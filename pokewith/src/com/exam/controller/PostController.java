package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.beans.PostBean;
import com.exam.beans.UserBean;
import com.exam.mapper.PostMapper;

@Controller
public class PostController {
	@Autowired
	PostMapper postmapper;
	
	@RequestMapping(value="/newpostin", method= {RequestMethod.GET, RequestMethod.POST})
	public String newpost(PostBean postBean, HttpServletResponse response, HttpServletRequest request) {
		System.out.println(postBean);
		postmapper.posting(postBean);
		request.setAttribute("m", "등록완료");
		return "newpost";
	}
	
	@ResponseBody
	@RequestMapping(value="/post", method= {RequestMethod.GET, RequestMethod.POST})
	public PostBean lookpost(HttpServletRequest request){
		PostBean postBean = new PostBean();
		HttpSession ss = request.getSession();
		String userId = (String) ss.getAttribute("userId");
		postBean.setUserId(userId);
		for(PostBean postBean2: postmapper.getPost(postBean)) {
			postBean = postBean2;
		}

		return postBean;
	}
	
}
