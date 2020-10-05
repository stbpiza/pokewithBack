package com.exam.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.exam.beans.CommentChat;
import com.exam.beans.PostBean;
import com.exam.chat.ChatRoom;
import com.exam.chat.ChatRoomForm;
import com.exam.chat.ChatRoomRepository;
import com.exam.mapper.CommentMapper;
import com.exam.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private final ChatRoomRepository chatRoomRepository;
	@Autowired
	PostMapper postmapper;
	@Autowired
	CommentMapper commentmapper;

	@GetMapping("/testroom")
	public String rooms(Model model) { //테스트
		logger.info("/testroom 접속");
		model.addAttribute("rooms", chatRoomRepository.findAllRoom());
		logger.info(chatRoomRepository.findAllRoom().size());
		return "rooms";
	}

	@GetMapping("/rooms/{id}")
	public String room(@PathVariable String id, Model model, HttpServletRequest request) {
		logger.info("/rooms/{id} 접속");
		try {
			HttpSession ss = request.getSession();
			String userId = (String)ss.getAttribute("userId");
			
			logger.info("userId : " + userId);
			
			PostBean postBean = new PostBean();
			postBean.setChat(id);
			for(PostBean postBean2: postmapper.checkChat(postBean)) {
				postBean = postBean2;
			}
			
			logger.info(postBean);
			
			if(!userId.contentEquals(postBean.getUserId())) { //글쓴사람 아니면 채팅방 입장 거부
				CommentChat commentChat = new CommentChat();
				commentChat.setUserId(userId);
				for(CommentChat commentChat2: commentmapper.checkChat(commentChat)) {
					commentChat = commentChat2;
				}
				if(!commentChat.getChat().contentEquals(id)) {//댓글쓴사람 아니면 채팅방 입장 거부(댓글 쓴거 있는상태)
					return "redirect:/";
				}
			}
			
			if(chatRoomRepository.findRoomById(id)==null) {
				if (postBean.getP_id()==null) {
					return "redirect:/";
				}
				else {
					ChatRoomForm form = new ChatRoomForm();
					form.setChat(id);
					form.setName(postBean.getUserId());
					chatRoomRepository.createChatRoom(form);
					
					ChatRoom room = chatRoomRepository.findRoomById(id);
					model.addAttribute("room", room);
					logger.info("room " + room.getRoomId());
					return "room";
				}
			}
			else {
				ChatRoom room = chatRoomRepository.findRoomById(id);
				model.addAttribute("room", room);
				logger.info("room " + room.getRoomId());
				return "room";
			}
		}
		catch(Exception e){
			logger.info(e);
			return "redirect:/";
		}
	}
	
	
	@GetMapping("/room/delete/{id}")
	public String deleteRoom(@PathVariable String id, Model model) { //채팅방 삭제기능(관리자)
		logger.info("/room/delete/{id} 접속");
		ChatRoom room = new ChatRoom();
		room.setRoomId(id);
		chatRoomRepository.deleteChatRoom(room);
		return "redirect:/testroom";
	}
	


}
