package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.beans.UserBean;
import com.exam.mapper.UserMapper;

@CrossOrigin
@Controller
public class FacebookController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
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
		logger.info("/facebook" + facebook_url);
		
		return "join";
	}
	

	
	@RequestMapping(value="/facebookSignInCallback", method= {RequestMethod.GET, RequestMethod.POST})
	public String facebookSignInCallback(@RequestParam String code, HttpServletRequest request) throws Exception{
		try {
			String redirectUri = oAuth2Parameters.getRedirectUri();
			logger.info("Redirect URI : " + redirectUri);
			logger.info("code : " + code);
			
			OAuth2Operations oauthOperations = connectionfactory.getOAuthOperations();
			AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, redirectUri, null);
			String accessToken = accessGrant.getAccessToken();
			logger.info("AccessToken : " + accessToken);
			Long expireTime = accessGrant.getExpireTime();
			
			if(expireTime != null && expireTime < System.currentTimeMillis()) {
				accessToken = accessGrant.getRefreshToken();
				logger.info("accessToken is expired. refresh token = " + accessToken);
			};
			
			Connection<Facebook> connection = connectionfactory.createConnection(accessGrant);
			Facebook facebook = connection == null ? new FacebookTemplate(accessToken) : connection.getApi();
			UserOperations userOperations = facebook.userOperations();
			
			try {
				String [] fields = { "id", "email", "name"};
				User userProfile = facebook.fetchObject("me", User.class, fields);
				logger.info("유저이메일 : " + userProfile.getEmail());
				logger.info("유저 id : " + userProfile.getId());
				logger.info("유저 name : " + userProfile.getName());
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
		logger.info("userid : " + userId);
		for(UserBean userBean2: usermapper.getUser(userBean)) {
			userBean = userBean2;
		}
		logger.info("bean nickname1 : " + userBean.getNickname1());
		if (userBean.getNickname1()==null) {
			return "register";
			//return "redirect:http://192.168.1.88:5502/register.html";
		}
		else {
			ss.setAttribute("nickname1", userBean.getNickname1());
			//ss.setAttribute("u_like", userBean.getU_like());
			//ss.setAttribute("u_hate", userBean.getU_hate());
		}
		
		return "index";
		//return "forward:http://192.168.1.88:5502/index.html";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/signup", method= RequestMethod.POST, produces="application/json; charset=utf8") //회원가입
	public String newsign(@RequestBody UserBean userBean, HttpServletResponse response, HttpServletRequest request) {
		HttpSession ss = request.getSession();
		String userId = (String) ss.getAttribute("userId");

		logger.info("userId : " + userId);
		logger.info(userBean);
		userBean.setUserId(userId);
		usermapper.signIn(userBean);
		
		for(UserBean userBean2: usermapper.getUser(userBean)) {
			userBean = userBean2;
		}
		ss.setAttribute("nickname1", userBean.getNickname1());
		//ss.setAttribute("u_like", userBean.getU_like());
		//ss.setAttribute("u_hate", userBean.getU_hate());
		return "1";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET) //테스트용
	public String logout(HttpServletRequest request) {
		HttpSession ss = request.getSession();
		ss.removeAttribute("userId");
		ss.removeAttribute("nickname1");
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/testlog", method= {RequestMethod.GET, RequestMethod.POST}) //테스트용 로그인
	public String testlogin(UserBean userBean, HttpServletResponse response, HttpServletRequest request) {
		logger.info("/testlog 접속");
		logger.info(userBean);
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(-1);
		session.setAttribute("userId",userBean.getUserId());
		logger.info("userId : " + userBean.getUserId());
		
		return "testlogin";
	}
	

}
