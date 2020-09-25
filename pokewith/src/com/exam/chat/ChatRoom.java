package com.exam.chat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ChatRoom {
	private String roomId;
	private String name;
	private Set<WebSocketSession> sessions = new HashSet<>();
	
	

	public static ChatRoom create(ChatRoomForm form) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.roomId = form.getChat();
//		chatRoom.roomId = UUID.randomUUID().toString();
		chatRoom.name = form.getName();
		System.out.println("테스트" + form);
		return chatRoom;
		
	}
	
	
	

	public void handleMessage(WebSocketSession session, ChatMessage chatMessage, ObjectMapper objectMapper)
			throws IOException {
		if (chatMessage.getType() == MessageType.ENTER) {
			sessions.add(session);
			chatMessage.setMessage(chatMessage.getWriter() + "님이 입장하셨습니다.");
		} else if (chatMessage.getType() == MessageType.LEAVE) {
			sessions.remove(session);
			chatMessage.setMessage(chatMessage.getWriter() + "님이 퇴장하셨습니다.");
		} else {
			chatMessage.setMessage(chatMessage.getWriter() + " : " + chatMessage.getMessage());
		}
		send(chatMessage, objectMapper);
	}

	private void send(ChatMessage chatMessage, ObjectMapper objectMapper) throws IOException {
		TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(chatMessage.getMessage()));
		for (WebSocketSession sess : sessions) {
			sess.sendMessage(textMessage);
		}
	}
}
