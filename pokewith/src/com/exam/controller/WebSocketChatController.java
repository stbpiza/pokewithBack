package com.exam.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.exam.chat.ChatMessage;
import com.exam.chat.ChatRoom;
import com.exam.chat.ChatRoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebSocketChatController extends TextWebSocketHandler {
	
		private Logger logger = Logger.getLogger(this.getClass());
		
		@Autowired
	 	private ChatRoomRepository chatRoomRepository;
		@Autowired
	    private ObjectMapper objectMapper;

	    @Override
	    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	        logger.info("메세지 전송 = " + session + ":" + message.getPayload());
	        String msg = message.getPayload();
	        ChatMessage chatMessage = objectMapper.readValue(msg,ChatMessage.class);
	        ChatRoom chatRoom = chatRoomRepository.findRoomById(chatMessage.getChatRoomId());
	        chatRoom.handleMessage(session,chatMessage,objectMapper);
	        }
}