package com.exam.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	PostMapper postmapper;
	@Autowired
	CommentMapper commentmapper;
	
	@ResponseBody
	@GetMapping//메인페이지 게시물 출력
	public List<PostBean> lookpost(){
		logger.info("/index get 접속");
		PostBean postBean = new PostBean();
		return postmapper.getPost(postBean);
	}
	
	@ResponseBody
	@PostMapping //메인페이지 게시물 입력
	public String setpost(@RequestBody PostBean postBean, HttpServletRequest request){
		logger.info("/index post 접속");
		HttpSession ss = request.getSession();
		String userId = (String) ss.getAttribute("userId");
		logger.info(postBean);
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
		CommentBean commentBean = new CommentBean();
		commentBean.setUserId(postBean.getUserId());
		String c_end="";
		for(CommentBean commentBean2: commentmapper.checkEnd(commentBean)) {
			c_end=commentBean2.getC_end();
		}
		if (c_end==null) {
			c_end="3";
		}
		logger.info("c_end : "+ c_end);
		if (c_end.contentEquals("0") || c_end.contentEquals("1")) {
			logger.info("댓글 쓴거 있음");
			return "-1";
		}
		logger.info("댓글 쓴거 없음, 정상 글 등록");
		postmapper.posting(postBean);
		return "1";
	}
	
	@ResponseBody
	@GetMapping("/three")//메인페이지 게시물 출력
	public List<PostBean> lookpost3(){
		logger.info("/index get 3 접속");
		PostBean postBean = new PostBean();
		return postmapper.getPost3(postBean);
	}

	@ResponseBody
	@GetMapping("/five")//메인페이지 게시물 출력
	public List<PostBean> lookpost5(){
		logger.info("/index get 5 접속");
		PostBean postBean = new PostBean();
		return postmapper.getPost5(postBean);
	}
	
	@ResponseBody
	@GetMapping("/mega")//메인페이지 게시물 출력
	public List<PostBean> lookpostm(){
		logger.info("/index get m 접속");
		PostBean postBean = new PostBean();
		return postmapper.getPostM(postBean);
	}
	
	@ResponseBody
	@GetMapping("/0")//메인페이지 게시물 출력
	public List<PostBean> look0post(){
		logger.info("/index/0 get 접속");
		PostBean postBean = new PostBean();
		return postmapper.get0Post(postBean);
	}
	
	@ResponseBody
	@GetMapping("/three/0")//메인페이지 게시물 출력
	public List<PostBean> look0post3(){
		logger.info("/index get 3/0 접속");
		PostBean postBean = new PostBean();
		return postmapper.get0Post3(postBean);
	}

	@ResponseBody
	@GetMapping("/five/0")//메인페이지 게시물 출력
	public List<PostBean> look0post5(){
		logger.info("/index get 5/0 접속");
		PostBean postBean = new PostBean();
		return postmapper.get0Post5(postBean);
	}
	
	@ResponseBody
	@GetMapping("/mega/0")//메인페이지 게시물 출력
	public List<PostBean> look0postm(){
		logger.info("/index get m/0 접속");
		PostBean postBean = new PostBean();
		return postmapper.get0PostM(postBean);
	}
}
