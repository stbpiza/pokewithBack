package com.exam.beans;

public class CommentChat {
	private String userId;
	private String chat;
	
	@Override
	public String toString() {
		return "CommentChat [userId=" + userId + ", chat=" + chat + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}
	
	
}
