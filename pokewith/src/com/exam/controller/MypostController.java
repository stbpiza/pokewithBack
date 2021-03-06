package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.beans.CommentBean;
import com.exam.beans.CommentBean2;
import com.exam.beans.PostBean;
import com.exam.beans.UserBean;
import com.exam.chat.ChatRoom;
import com.exam.chat.ChatRoomRepository;
import com.exam.mapper.CommentMapper;
import com.exam.mapper.PostMapper;
import com.exam.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@RequestMapping(value="/mypost", produces="application/json; charset=utf8")
public class MypostController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private final ChatRoomRepository chatRoomRepository;
	
	@Autowired
	PostMapper postmapper;
	@Autowired
	CommentMapper commentmapper;
	@Autowired
	UserMapper usermapper;
	
	@ResponseBody
	@GetMapping()
	public PostBean lookmypost(HttpServletRequest request){
		logger.info("/mypost get 접속");
		PostBean postBean = new PostBean();
		CommentBean commentBean = new CommentBean();
		
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		logger.info("userId : "+ userId);
		commentBean.setUserId(userId);
		for(CommentBean commentBean2: commentmapper.checkEnd(commentBean)) {
			commentBean = commentBean2;
		}
		String c_end = commentBean.getC_end();
		logger.info("c_end : "+ c_end);
		if(c_end==null) {
			c_end="3";
		}
		if(c_end.contentEquals("0") || c_end.contentEquals("1")) {
			logger.info("댓글쓴거있음");
			postBean.setP_id(commentBean.getP_id());
			for(PostBean postBean2: postmapper.getMyPost2(postBean)) {
				postBean = postBean2;
			}
			postBean.setSuserId(userId);
			return postBean;
		}
		else {
			logger.info("댓글쓴거없거나 채택안됨");
			postBean.setUserId(userId);
			for(PostBean postBean2: postmapper.getMyPost(postBean)) {
				postBean = postBean2;
			}
			String p_end = postBean.getP_end();
			logger.info("p_end : " + p_end);
			postBean.setSuserId(userId);
			return postBean;
		}
	}
	
	@ResponseBody
	@PutMapping
	public String startRaid(@RequestBody CommentBean2 commentBean2) { //댓글채택
		logger.info("/mypost put 접속 (채택)");
		logger.info(commentBean2);
		try {
			commentmapper.changeComment2(commentBean2); //전부 2로
			commentmapper.changeComment1(commentBean2); //채택된 댓글만 1로
			
			PostBean postBean = new PostBean();
			postBean.setP_id(commentBean2.getP_id());
			postBean.setChat(commentBean2.getChat());
			postmapper.pEnd1(postBean); //글 p_end 1로하고 채팅방 이름 추가
			
			return "1";
		}
		catch(Exception e){
			logger.info(e);
			return "-2";
		}
	}
	
	@ResponseBody
	@PostMapping("/end")
	public String endRaid(@RequestBody PostBean postBean) { //레이드 종료
		logger.info("/mypost/end post 접속 (종료)");
		logger.info(postBean);
		try {
			postmapper.pEnd2(postBean);
	
			CommentBean2 commentBean2 = new CommentBean2();
			commentBean2.setP_id(postBean.getP_id());
			commentmapper.changeComment2(commentBean2);
			
			ChatRoom room = new ChatRoom();
			room.setRoomId(postBean.getChat());
			chatRoomRepository.deleteChatRoom(room);
			
			return"1";
		}
		catch(Exception e){
			logger.info(e);
			return "-2";
		}
	}
	
	@ResponseBody
	@DeleteMapping("/mycomment")
	public String delComment(@RequestBody CommentBean commentBean) { //댓글 삭제
		logger.info("/mypost/mycomment delete 접속");
		logger.info(commentBean);
		try {
			commentmapper.commentDel(commentBean);
			return"1";
		}
		catch(Exception e){
			logger.info(e);
			return "-2";
		}
	}
	
	@ResponseBody
	@PostMapping("/all")
	public String clean(HttpServletRequest request) { //모든 글 댓글 end 2로
		logger.info("/mypost/all post 접속");
		try {
			HttpSession ss = request.getSession();
			String userId = (String)ss.getAttribute("userId");
			
			UserBean userBean = new UserBean();
			userBean.setUserId(userId);
			
			usermapper.cleanUser(userBean);
			
			
			return "1";
		}
		catch(Exception e){
			logger.info(e);
			return "-2";
		}
	}
}
