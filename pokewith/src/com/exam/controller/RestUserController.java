package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.beans.UserBean;
import com.exam.mapper.UserMapper;

@RestController
public class RestUserController {
	
	@Autowired
	UserMapper usermapper;
	
	@RequestMapping(value="/relogin", method= {RequestMethod.GET, RequestMethod.POST}) //테스트용
	public UserBean setUsertest(HttpServletRequest request) {
		UserBean userBean = new UserBean();
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		userBean.setUserId(userId);
		System.out.println("userid : " + userId);
		for(UserBean userBean2: usermapper.getUser(userBean)) {
			userBean = userBean2;
		}
		return userBean;
	}
	
//	@RequestMapping(value="/mypage", method= {RequestMethod.GET, RequestMethod.POST}) //유저정보출력
//	public UserBean getUser(HttpServletRequest request) {
//		UserBean userBean = new UserBean();
//		HttpSession ss = request.getSession();
//		String userId = (String)ss.getAttribute("userId");
//		userBean.setUserId(userId);
//		System.out.println("userid : " + userId);
//		for(UserBean userBean2: usermapper.getUser(userBean)) {
//			userBean = userBean2;
//		}
//		return userBean;
//	}
	
	
	
}
