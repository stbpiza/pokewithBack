package com.exam.chat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatRoomForm {
	private String name;
	private String chat;
	@Override
	public String toString() {
		return "ChatRoomForm [name=" + name + ", chat=" + chat + "]";
	}
	

}

