package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exam.chat.ChatRoom;
import com.exam.chat.ChatRoomForm;
import com.exam.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
	private final ChatRoomRepository chatRoomRepository;

	@GetMapping("/")
	public String rooms(Model model) {
		log.info("log 확인해보기");
		model.addAttribute("hello", "hiiiiiii");
		model.addAttribute("rooms", chatRoomRepository.findAllRoom());
		System.out.println(chatRoomRepository.findAllRoom().size());
		return "rooms";
	}

//    @PostMapping("/room2")
//    public String rooms2(Model model){
//        model.addAttribute("rooms",chatRoomRepository.findAllRoom());
//        return "forward:/views/rooms.jsp";
//    }

	@GetMapping("/rooms/{id}")
	public String room(@PathVariable String id, Model model) {
		ChatRoom room = chatRoomRepository.findRoomById(id);
		model.addAttribute("room", room);
		System.out.println("room " + room.getRoomId());
		return "room";
	}
	
	
    @GetMapping("/new")
	public String make(Model model) {
		ChatRoomForm form = new ChatRoomForm();
		model.addAttribute("form", form);
		return "newRoom";
	}

//	@PostMapping("/room/new")
//	public String makeRoom(ChatRoomForm form) {
//		chatRoomRepository.createChatRoom(form.getName());
//		System.out.println("name " + form.getName());
//		return "redirect:/";
//	}
	
	@RequestMapping(value = "/room/new", method=RequestMethod.POST)
	public String makeRoom(@RequestBody ChatRoomForm form) {
		chatRoomRepository.createChatRoom(form);
		System.out.println("name " + form.getName());
		System.out.println("json test" + form);
		return "redirect:/";
	}
	
	@DeleteMapping("/room/delete/{id}")
	public String deleteRoom(@PathVariable String id, Model model) {
		ChatRoom room = new ChatRoom();
		room.setRoomId(id);
		chatRoomRepository.deleteChatRoom(room);
		return "redirect:/";
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
