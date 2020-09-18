package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.beans.UserBean;
import com.exam.mapper.UserMapper;

@CrossOrigin
@Controller
@RequestMapping(value="/mypage", produces="application/json; charset=utf8")
public class MypageController {
	
	@Autowired
	UserMapper usermapper;

	@ResponseBody
	@GetMapping //유저정보출력
	public UserBean getUser(@RequestBody UserBean userBean, HttpServletRequest request) {
		System.out.println("/mypage get 접속");
		//UserBean userBean = new UserBean();
		if (userBean.getUserId()==null) {
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		userBean.setUserId(userId);
		}
		System.out.println("userid : " + userBean.getUserId());
		for(UserBean userBean2: usermapper.getUser(userBean)) {
			userBean = userBean2;
		}
		return userBean;
	}
	
	@PostMapping //유저정보수정
	public String setUser(@RequestBody UserBean userBean, HttpServletRequest request) {
		System.out.println("/mypage post 접속");
		System.out.println(userBean);
		System.out.println(userBean.getUserId()==null);
		if (userBean.getUserId()==null) {
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		userBean.setUserId(userId);
		}
		System.out.println("userid : " + userBean.getUserId());
		
		usermapper.reUser(userBean);
		return "userinfo";
	}
}
