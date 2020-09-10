package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Component
public class WebSocketChatController extends TextWebSocketHandler {
	private List<WebSocketSession> sessions = new ArrayList<>(); 
	
	//List로 접속한 session들을 관리하고,handleTextMessage에서 메세지를 접속한 세션들 모두에게 뿌려줌으로서 채팅이 이루어지는 부분
	 
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("접속 : {}",  session);
    }
 
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("메세지 전송 = {} : {}",session,message.getPayload());
        for(WebSocketSession sess : sessions){
            TextMessage msg = new TextMessage(message.getPayload());
            sess.sendMessage(msg);
        }
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        log.info("퇴장 : {}",  session);
    }

	
	
}
