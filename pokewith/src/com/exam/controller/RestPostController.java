package com.exam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.beans.PostBean;
import com.exam.mapper.PostMapper;

@RestController
public class RestPostController {
	
	@Autowired
	PostMapper postmapper;
	
	
//	@RequestMapping(value="/mypost", method= {RequestMethod.GET, RequestMethod.POST}) //마이페이지 게시물 출력
//	public PostBean lookmypost(HttpServletRequest request){
//		PostBean postBean = new PostBean();
//		HttpSession ss = request.getSession();
//		String userId = (String) ss.getAttribute("userId");
//		postBean.setUserId(userId);
//		for(PostBean postBean2: postmapper.getMyPost(postBean)) {
//			postBean = postBean2;
//		}
//		System.out.println(postBean);
//		return postBean;
//	}
}
