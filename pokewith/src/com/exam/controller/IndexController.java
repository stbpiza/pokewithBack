package com.exam.controller;

import java.util.List;

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

import com.exam.beans.CommentBean;
import com.exam.beans.PostBean;
import com.exam.mapper.CommentMapper;
import com.exam.mapper.PostMapper;

@CrossOrigin
@Controller
@RequestMapping(value="/index", produces="application/json; charset=utf8")
public class IndexController {
	
	@Autowired
	PostMapper postmapper;
	@Autowired
	CommentMapper commentmapper;
	
	@ResponseBody
	@GetMapping//메인페이지 게시물 출력
	public List<PostBean> lookpost(){
		System.out.println("/index get 접속");
		PostBean postBean = new PostBean();

		
		return postmapper.getPost(postBean);
	}
	
	@PostMapping //메인페이지 게시물 입력
	public String setpost(@RequestBody PostBean postBean, HttpServletRequest request){
		System.out.println("/index post 접속");
		HttpSession ss = request.getSession();
		String userId = (String) ss.getAttribute("userId");
		System.out.println(postBean);
		postBean.setUserId(userId);

		postmapper.posting(postBean);
		String m = "성공";
		request.setAttribute("m", m);
		return "newpost";
	}
	
	@ResponseBody
	@GetMapping("/comment") //댓글 출력
	public List<CommentBean> lookcomment(@RequestBody CommentBean commentBean){
		System.out.println("/index/comment get 접속");
		System.out.println(commentBean);
		
		return commentmapper.getComment(commentBean);
	}
	
	@ResponseBody
	@PostMapping("/comment") //댓글 입력
	public String setcomment(@RequestBody CommentBean commentBean, HttpServletRequest request) {
		System.out.println("/index/comment post 접속");

		HttpSession ss = request.getSession();
		String userId = (String) ss.getAttribute("userId");
		System.out.println(commentBean);
		commentBean.setUserId(userId);

		commentmapper.commenting(commentBean);
		String m = "성공";
		request.setAttribute("m", m);
		return "newcomment";
	}
}
