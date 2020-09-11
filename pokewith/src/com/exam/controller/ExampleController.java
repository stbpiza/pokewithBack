package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExampleController {
//	@RequestMapping(value="/", method = RequestMethod.GET)
//	public String home() {return "facebook";}
	
	@RequestMapping(value="/facebook", method = RequestMethod.GET)
	public String home3() {return "facebook";}
	
}
