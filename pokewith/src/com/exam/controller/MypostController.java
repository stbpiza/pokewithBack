package com.exam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.beans.CommentBean;
import com.exam.beans.PostBean;
import com.exam.mapper.CommentMapper;
import com.exam.mapper.PostMapper;

@Controller
@RequestMapping(value="/mypost", produces="application/json; charset=utf8")
public class MypostController {
	@Autowired
	PostMapper postmapper;
	@Autowired
	CommentMapper commentmapper;
	
	@ResponseBody
	@GetMapping
	public PostBean lookmypost(HttpServletRequest request){
		System.out.println("/mypost get 접속");
		PostBean postBean = new PostBean();
		CommentBean commentBean = new CommentBean();
		
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		
		postBean.setUserId(userId);
		for(PostBean postBean2: postmapper.getMyPost(postBean)) {
			postBean = postBean2;
		}
		String p_end = postBean.getP_end();
		System.out.println("p_end : " + p_end);
		if (p_end!=null) {
			return postBean;
		}
		
		return postBean;
	}
}
