package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	UserMapper usermapper;

	@ResponseBody
	@GetMapping() //유저정보출력
	public UserBean getUser(HttpServletRequest request) {
		logger.info("/mypage get 접속");
		UserBean userBean = new UserBean();
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		userBean.setUserId(userId);
		logger.info("userid : "+ userBean.getUserId());
		for(UserBean userBean2: usermapper.getUser(userBean)) {
			userBean = userBean2;
		}
		return userBean;
	}
	
	@PostMapping //유저정보수정
	public String setUser(@RequestBody UserBean userBean, HttpServletRequest request) {
		logger.info("/mypage post 접속"+ userBean);
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		userBean.setUserId(userId);
		logger.info("userid : "+ userBean.getUserId());
		usermapper.reUser(userBean);
		ss.setAttribute("nickname1", userBean.getNickname1());
		return "mypage";
	}
}
