package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(value="/", method=RequestMethod.GET) 
	public String main() {
		return "index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/post", method=RequestMethod.GET)
	public String mypost() {
		return "mypost";
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public String mypage() {
		return "mypage";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	
}
