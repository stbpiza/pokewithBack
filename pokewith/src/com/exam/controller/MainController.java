package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	
	
	@RequestMapping(value="/test", method= {RequestMethod.GET, RequestMethod.POST}) //테스트용
	public String test() {
		return "testlogin";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET) //테스트용
	public String main() {
		return "index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET) //테스트용
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/post", method=RequestMethod.GET) //테스트용
	public String mypost() {
		return "mypost";
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET) //테스트용
	public String mypage() {
		return "mypage";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET) //테스트용
	public String register() {
		return "register";
	}
	
	
}
