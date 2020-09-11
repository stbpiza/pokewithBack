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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FacebookController {
	
	@Autowired
	private FacebookConnectionFactory connectionfactory;
	@Autowired
	private OAuth2Parameters oAuth2Parameters;
	
	@RequestMapping(value="/sign", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(HttpServletResponse response, Model model) {
		
		OAuth2Operations oauthOperations = connectionfactory.getOAuthOperations();
		String facebook_url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		
		model.addAttribute("facebook_url", facebook_url);
		System.out.println("/facebook" + facebook_url);
		
		return "/join";
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
				session.setMaxInactiveInterval(86400);
				session.setAttribute("userId",userProfile.getId());
				session.setAttribute("userName",userProfile.getName());
			}
			catch (MissingAuthorizationException e) {
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/facebook";
	} 
	

}
