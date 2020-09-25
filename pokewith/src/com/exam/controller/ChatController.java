package com.exam.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exam.chat.ChatRoom;
import com.exam.chat.ChatRoomForm;
import com.exam.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private final ChatRoomRepository chatRoomRepository;

	@GetMapping("/testroom")
	public String rooms(Model model) {
		logger.info("/testroom 접속");
		model.addAttribute("hello", "hiiiiiii");
		model.addAttribute("rooms", chatRoomRepository.findAllRoom());
		logger.info(chatRoomRepository.findAllRoom().size());
		return "rooms";
	}

//    @PostMapping("/room2")
//    public String rooms2(Model model){
//        model.addAttribute("rooms",chatRoomRepository.findAllRoom());
//        return "forward:/views/rooms.jsp";
//    }

	@GetMapping("/rooms/{id}")
	public String room(@PathVariable String id, Model model) {
		logger.info("/rooms/{id} 접속");
		ChatRoom room = chatRoomRepository.findRoomById(id);
		model.addAttribute("room", room);
		logger.info("room " + room.getRoomId());
		return "room";
	}
	
	
//    @GetMapping("/new")
//	public String make(Model model) {
//		ChatRoomForm form = new ChatRoomForm();
//		model.addAttribute("form", form);
//		return "newRoom";
//	}

//	@PostMapping("/room/new")
//	public String makeRoom(ChatRoomForm form) {
//		chatRoomRepository.createChatRoom(form.getName());
//		System.out.println("name " + form.getName());
//		return "redirect:/";
//	}
	
	@RequestMapping(value = "/room/new", method=RequestMethod.POST, produces="application/json; charset=utf8")
	public String makeRoom(@RequestBody ChatRoomForm form) {
		logger.info("/room/new 접속");
		logger.info("form : " + form);
		chatRoomRepository.createChatRoom(form);
		logger.info("name " + form.getName());
		logger.info("json test" + form);
		return "redirect:/post";
	}
	
	@DeleteMapping("/room/delete/{id}")
	public String deleteRoom(@PathVariable String id, Model model) {
		logger.info("/room/delete/{id} 접속");
		ChatRoom room = new ChatRoom();
		room.setRoomId(id);
		chatRoomRepository.deleteChatRoom(room);
		return "redirect:/post";
	}
	
//	@RequestMapping(value = "/json" , method = RequestMethod.POST)
//	@ResponseBody
//	public void jsonTest(RequestBody Map<String, Object> human) {
//		Logger.info(human.toString());
//	}

//  @GetMapping("/")
//  public String rooms(Model model){
//      model.addAttribute("rooms",chatRoomRepository.findAllRoom());
//      return "rooms";
//  }
//
//  @GetMapping("/rooms/{id}")
//  public String room(@PathVariable String id, Model model){
//      ChatRoom room = chatRoomRepository.findRoomById(id);
//      model.addAttribute("room",room);
//      return "room";
//  }
//
//  @GetMapping("/new")
//  public String make(Model model){
//      ChatRoomForm form = new ChatRoomForm();
//      model.addAttribute("form",form);
//      return "newRoom";
//  }
//
//  @PostMapping("/room/new")
//  public String makeRoom(ChatRoomForm form){
//      chatRoomRepository.createChatRoom(form.getName());
//
//      return "redirect:/";
//  }

//	@GetMapping("/")
//	public String chat() {
//		return "chat";
//	}

}
