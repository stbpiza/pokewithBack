package com.exam.beans;

import java.util.List;

public class CommentBean2 {
	private String p_id;
	private List<String> c_id;
	private String chat;
	
	
	@Override
	public String toString() {
		return "CommentBean2 [p_id=" + p_id + ", c_id=" + c_id + ", chat=" + chat + "]";
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public List<String> getC_id() {
		return c_id;
	}
	public void setC_id(List<String> c_id) {
		this.c_id = c_id;
	}
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}
	
	
}
