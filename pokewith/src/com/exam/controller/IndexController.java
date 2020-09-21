package com.exam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	@ResponseBody
	@PostMapping //메인페이지 게시물 입력
	public String setpost(@RequestBody PostBean postBean, HttpServletRequest request){
		System.out.println("/index post 접속");
//		HttpSession ss = request.getSession();
//		String userId = (String) ss.getAttribute("userId");
		System.out.println(postBean);
//		postBean.setUserId(userId);
		
		String p_end="";
		for(PostBean postBean2: postmapper.checkEnd(postBean)) {
			p_end=postBean2.getP_end();
		}
		if (p_end==null) {
			p_end="3";
		}
		System.out.println("p_end : " + p_end);
		if (p_end.contentEquals("0")) {
			System.out.println("이미 글 쓴거 있음");
			return "-1";
		}
		System.out.println("글 쓴거 없음");
		CommentBean commentBean = new CommentBean();
		commentBean.setUserId(postBean.getUserId());
		String c_end="";
		for(CommentBean commentBean2: commentmapper.checkEnd(commentBean)) {
			c_end=commentBean2.getC_end();
		}
		if (c_end==null) {
			c_end="3";
		}
		System.out.println("c_end : " + c_end);
		if (c_end.contentEquals("0") || c_end.contentEquals("1")) {
			System.out.println("댓글 쓴거 있음");
			return "-1";
		}
		System.out.println("댓글 쓴거 없음");
		System.out.println("정상 글 등록");
		postmapper.posting(postBean);
		return "1";
	}
	

}
