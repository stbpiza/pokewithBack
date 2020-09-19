package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exam.beans.UserBean;
import com.exam.mapper.UserMapper;

@Controller
public class UserController {
	
	@Autowired
	UserMapper usermapper;
	
	@RequestMapping(value="/reuser", method= {RequestMethod.GET, RequestMethod.POST}) //회원정보수정
	public String reuser(UserBean userBean, HttpServletResponse response, HttpServletRequest request) {
		System.out.println(userBean);
		HttpSession ss = request.getSession();
		String userId = (String) ss.getAttribute("userId");
		System.out.println("userId : " + userId);
		userBean.setUserId(userId);
		usermapper.reUser(userBean);
		
		return "userinfo";
	}
	
	@RequestMapping(value="/testlog", method= {RequestMethod.GET, RequestMethod.POST}) //테스트용 로그인
	public String testlogin(UserBean userBean, HttpServletResponse response, HttpServletRequest request) {
		System.out.println("/testlog 접속");
		System.out.println(userBean);
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(-1);
		session.setAttribute("userId",userBean.getUserId());
		System.out.println("userId : " + userBean.getUserId());
		
		return "testlogin";
	}
	
}
