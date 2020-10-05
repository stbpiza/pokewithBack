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

import com.exam.beans.CommentBean;
import com.exam.beans.CommentIndex;
import com.exam.beans.CommentMypost;
import com.exam.beans.PostBean;
import com.exam.beans.UserBean;
import com.exam.mapper.CommentMapper;
import com.exam.mapper.PostMapper;
import com.exam.mapper.UserMapper;

@CrossOrigin
@Controller
@RequestMapping(value="/comment", produces="application/json; charset=utf8")
public class CommentController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	PostMapper postmapper;
	@Autowired
	CommentMapper commentmapper;
	@Autowired
	UserMapper usermapper;
	
	@ResponseBody
	@GetMapping("/{p_id}") //댓글 출력
	public CommentIndex lookcomment(@PathVariable String p_id, HttpServletRequest request){
		logger.info("/comment get 접속");
		logger.info("p_id : "+ p_id);
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		CommentBean commentBean = new CommentBean();
		commentBean.setP_id(p_id);
		
		UserBean userBean = new UserBean();
		userBean.setUserId(userId);
		
		return new CommentIndex(commentmapper.getComment(commentBean), usermapper.getUser(userBean));
	}
	
	@ResponseBody
	@GetMapping("/mypost/{p_id}") //댓글 출력
	public CommentMypost lookmycomment(@PathVariable String p_id, HttpServletRequest request){
		logger.info("/comment get 접속");
		logger.info("p_id : "+ p_id);
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		CommentBean commentBean = new CommentBean();
		commentBean.setP_id(p_id);
		
		
		return new CommentMypost(commentmapper.getComment(commentBean), userId);
	}
	
	@ResponseBody
	@PostMapping //댓글 입력
	public String setcomment(@RequestBody CommentBean commentBean, HttpServletRequest request) {
		logger.info("/comment post 접속");

		HttpSession ss = request.getSession();
		String userId = (String) ss.getAttribute("userId");
		logger.info(commentBean);
		commentBean.setUserId(userId);

		PostBean postBean = new PostBean();
		postBean.setUserId(userId);
		String p_end="";
		for(PostBean postBean2: postmapper.checkEnd(postBean)) {
			p_end=postBean2.getP_end();
		}
		if (p_end==null) {
			p_end="3";
		}
		logger.info("p_end : "+ p_end);
		if (p_end.contentEquals("0") || p_end.contentEquals("1")) {
			logger.info("이미 글 쓴거 있음");
			return "-1";
		}
		logger.info("글 쓴거 없음");

		String c_end="";
		for(CommentBean commentBean2: commentmapper.checkEnd(commentBean)) {
			c_end=commentBean2.getC_end();
		}
		if (c_end==null) {
			c_end="3";
		}
		logger.info("c_end : " + c_end);
		if (c_end.contentEquals("0") || c_end.contentEquals("1")) {
			logger.info("댓글 쓴거 있음");
			return "-1";
		}
		logger.info("댓글 쓴거 없음, 정상 댓글 등록");
		
		
		commentmapper.commenting(commentBean);

		return "1";
	}
}
