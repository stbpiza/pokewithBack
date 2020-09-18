package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.beans.UserBean;
import com.exam.mapper.UserMapper;

@Controller
public class FacebookController {
	
	@Autowired
	private FacebookConnectionFactory connectionfactory;
	@Autowired
	private OAuth2Parameters oAuth2Parameters;
	@Autowired
	UserMapper usermapper;
	
	@RequestMapping(value="/sign", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(HttpServletResponse response, Model model) {
		
		OAuth2Operations oauthOperations = connectionfactory.getOAuthOperations();
		String facebook_url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		
		model.addAttribute("facebook_url", facebook_url);
		System.out.println("/facebook" + facebook_url);
		
		return "join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join2() {
		return "join";
	}
	
	@RequestMapping(value="/facebookSignInCallback", method= {RequestMethod.GET, RequestMethod.POST})
	public String facebookSignInCallback(@RequestParam String code, HttpServletRequest request) throws Exception{
		try {
			String redirectUri = oAuth2Parameters.getRedirectUri();
			System.out.println("Redirect URI : " + redirectUri);
			System.out.println("code : " + code);
			
			OAuth2Operations oauthOperations = connectionfactory.getOAuthOperations();
			AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, redirectUri, null);
			String accessToken = accessGrant.getAccessToken();
			System.out.println("AccessToken : " + accessToken);
			Long expireTime = accessGrant.getExpireTime();
			
			if(expireTime != null && expireTime < System.currentTimeMillis()) {
				accessToken = accessGrant.getRefreshToken();
				//logger.info("accessToken is expired. refresh token = {}", accessToken);
			};
			
			Connection<Facebook> connection = connectionfactory.createConnection(accessGrant);
			Facebook facebook = connection == null ? new FacebookTemplate(accessToken) : connection.getApi();
			UserOperations userOperations = facebook.userOperations();
			
			try {
				String [] fields = { "id", "email", "name"};
				User userProfile = facebook.fetchObject("me", User.class, fields);
				System.out.println("유저이메일 : " + userProfile.getEmail());
				System.out.println("유저 id : " + userProfile.getId());
				System.out.println("유저 name : " + userProfile.getName());
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(-1);
				session.setAttribute("userId",userProfile.getId());
			}
			catch (MissingAuthorizationException e) {
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:/check";
	} 
	
	@RequestMapping(value="/check", method= {RequestMethod.GET, RequestMethod.POST}) //회원유무체크
	public String join(UserBean userBean, HttpServletResponse response, HttpServletRequest request) {
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		userBean.setUserId(userId);
		System.out.println("userid : " + userId);
		for(UserBean userBean2: usermapper.getUser(userBean)) {
			userBean = userBean2;
		}
		System.out.println("bean nickname1 : " + userBean.getNickname1());
		if (userBean.getNickname1()==null) {
			return "signin";
			//return "redirect:http://192.168.1.88:5502/register.html";
		}
		else {
			//ss.setAttribute("nickname1", userBean.getNickname1());
			//ss.setAttribute("u_like", userBean.getU_like());
			//ss.setAttribute("u_hate", userBean.getU_hate());
		}
		
		return "loginGood";
		//return "forward:http://192.168.1.88:5502/index.html";
	}
	
	
	
	@RequestMapping(value="/signup", method= {RequestMethod.GET, RequestMethod.POST}) //회원가입
	public String newsign(UserBean userBean, HttpServletResponse response, HttpServletRequest request) {
		HttpSession ss = request.getSession();
		String userId = (String) ss.getAttribute("userId");

		System.out.println("userId : " + userId);
		System.out.println(userBean);
		userBean.setUserId(userId);
		usermapper.signIn(userBean);
		
		for(UserBean userBean2: usermapper.getUser(userBean)) {
			userBean = userBean2;
		}
		//ss.setAttribute("nickname1", userBean.getNickname1());
		//ss.setAttribute("u_like", userBean.getU_like());
		//ss.setAttribute("u_hate", userBean.getU_hate());
		return "loginGood";
	}
	
	@ResponseBody //테스트용
	@RequestMapping(value="/newsignjson", method= RequestMethod.POST, produces="application/json; charset=utf8")
	public UserBean gsonin(@RequestBody UserBean userBean){

		
		System.out.println("in" + userBean);
		for(UserBean userBean2: usermapper.getUser(userBean)) {
			userBean = userBean2;
		}
		System.out.println("out" + userBean);
		return userBean;
	}
	
	@RequestMapping(value="/updateuser", method=RequestMethod.GET) //테스트용
	public String uduser() {
		return "updateuser";
	}
	@RequestMapping(value="/posttest", method=RequestMethod.GET) //테스트용
	public String ptest() {
		return "post";
	}
	@RequestMapping(value="/newpost", method=RequestMethod.GET) //테스트용
	public String nptest() {
		return "newpost";
	}
}
