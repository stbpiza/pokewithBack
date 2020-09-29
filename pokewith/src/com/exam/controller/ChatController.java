package com.exam.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.beans.PostBean;
import com.exam.chat.ChatRoom;
import com.exam.chat.ChatRoomForm;
import com.exam.chat.ChatRoomRepository;
import com.exam.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private final ChatRoomRepository chatRoomRepository;
	@Autowired
	PostMapper postmapper;

	@GetMapping("/testroom")
	public String rooms(Model model) { //테스트
		logger.info("/testroom 접속");
		model.addAttribute("hello", "hiiiiiii");
		model.addAttribute("rooms", chatRoomRepository.findAllRoom());
		logger.info(chatRoomRepository.findAllRoom().size());
		return "rooms";
	}

	@GetMapping("/rooms/{id}")
	public String room(@PathVariable String id, Model model) {
		logger.info("/rooms/{id} 접속");


		if(chatRoomRepository.findRoomById(id)==null) {
			PostBean postBean = new PostBean();
			postBean.setChat(id);
			for(PostBean postBean2: postmapper.checkChat(postBean)) {
				postBean = postBean2;
			}
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
	
//	@ResponseBody
//	@DeleteMapping("/room/delete/{id}")
//	public String deleteRoom(@PathVariable String id, Model model) {
//		logger.info("/room/delete/{id} 접속");
//		ChatRoom room = new ChatRoom();
//		room.setRoomId(id);
//		chatRoomRepository.deleteChatRoom(room);
//		return "1";
//	}
	


}
