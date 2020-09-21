package com.exam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.beans.CommentBean;
import com.exam.beans.CommentBean2;
import com.exam.beans.PostBean;
import com.exam.mapper.CommentMapper;
import com.exam.mapper.PostMapper;

@CrossOrigin
@Controller
@RequestMapping(value="/mypost", produces="application/json; charset=utf8")
public class MypostController {
	@Autowired
	PostMapper postmapper;
	@Autowired
	CommentMapper commentmapper;
	
	@ResponseBody
	@GetMapping("/{userId}")
	public PostBean lookmypost(@PathVariable String userId, HttpServletRequest request){
		System.out.println("/mypost get 접속");
		PostBean postBean = new PostBean();
		CommentBean commentBean = new CommentBean();
		
//		HttpSession ss = request.getSession();
//		String userId = (String)ss.getAttribute("userId");
		System.out.println("userId : " + userId);
		commentBean.setUserId(userId);
		for(CommentBean commentBean2: commentmapper.checkEnd(commentBean)) {
			commentBean = commentBean2;
		}
		String c_end = commentBean.getC_end();
		System.out.println("c_end : " + c_end);
		if(c_end==null) {
			c_end="3";
		}
		if(c_end.contentEquals("0") || c_end.contentEquals("1")) {
			System.out.println("댓글쓴거있음");
			postBean.setP_id(commentBean.getP_id());
			for(PostBean postBean2: postmapper.getMyPost2(postBean)) {
				postBean = postBean2;
			}
			return postBean;
		}
		else {
			System.out.println("댓글쓴거없거나 채택안됨");
			postBean.setUserId(userId);
			for(PostBean postBean2: postmapper.getMyPost(postBean)) {
				postBean = postBean2;
			}
			String p_end = postBean.getP_end();
			System.out.println("p_end : " + p_end);
			
			return postBean;
		}
	}
	
	@ResponseBody
	@PutMapping
	public String startRaid(@RequestBody CommentBean2 commentBean2) { //댓글채택
		System.out.println("/mypost put 접속 (채택)");
		System.out.println(commentBean2);
		
		commentmapper.changeComment2(commentBean2); //전부 2로
		commentmapper.changeComment1(commentBean2); //채택된 댓글만 1로
		
		PostBean postBean = new PostBean();
		postBean.setP_id(commentBean2.getP_id());
		postBean.setChat(commentBean2.getChat());
		postmapper.pEnd1(postBean); //글 p_end 1로하고 채팅방 이름 추가
		
		return "1";
	}
	
	@ResponseBody
	@PostMapping("/end")
	public String endRaid(@RequestBody PostBean postBean) { //레이드 종료
		System.out.println("/mypost post 접속 (종료)");
		System.out.println(postBean);
		postmapper.pEnd2(postBean);

		CommentBean2 commentBean2 = new CommentBean2();
		commentBean2.setP_id(postBean.getP_id());
		commentmapper.changeComment2(commentBean2);
		return"1";
	}
}
