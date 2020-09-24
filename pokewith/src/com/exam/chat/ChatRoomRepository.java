package com.exam.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Repository
public class ChatRoomRepository {
	private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){ //방 전체 목록 출력하기
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id){ //메세지를 받아서 해당 룸번호에만 전달하기
    	
        return chatRoomMap.get(id);
    }
    

    public ChatRoom createChatRoom(@RequestBody ChatRoomForm form){ //새로운방 생성 메소드
        ChatRoom chatRoom = ChatRoom.create(form);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        System.out.println("key : " + chatRoom.getRoomId());
        System.out.println("방이름 " + chatRoom.getName());
        return chatRoom;
    }
	
    public void deleteChatRoom(ChatRoom room) { //방 삭제 메소드
    	System.out.println("deleteChatRoom");
    	System.out.println(room.getRoomId());
    	System.out.println("size : " + chatRoomMap.size());
    	System.out.println("---------------------------------");
    	System.out.println("contains ? " + chatRoomMap.containsKey(room.getRoomId()));
        chatRoomMap.remove(room.getRoomId());
        
        System.out.println("size : " + chatRoomMap.size());
    }
   
    

}
