package com.exam.beans;


public class PostBean {
	private String p_id;
	private String userId;
	private String pokemon;
	private String raidLevel;
	private String startTime;
	private String endTime;
	private String minLevel;
	private String nPass;
	private String rPass;
	private String p_end;
	private String nickname1;
	private String u_like;
	private String u_hate;
	private String chat;
	private String suserId;
	
	
	
	@Override
	public String toString() {
		return "PostBean [p_id=" + p_id + ", userId=" + userId + ", pokemon=" + pokemon + ", raidLevel=" + raidLevel
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", minLevel=" + minLevel + ", nPass=" + nPass
				+ ", rPass=" + rPass + ", p_end=" + p_end + ", nickname1=" + nickname1 + ", u_like=" + u_like
				+ ", u_hate=" + u_hate + ", chat=" + chat + ", suserId=" + suserId + "]";
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPokemon() {
		return pokemon;
	}
	public void setPokemon(String pokemon) {
		this.pokemon = pokemon;
	}
	public String getRaidLevel() {
		return raidLevel;
	}
	public void setRaidLevel(String raidLevel) {
		this.raidLevel = raidLevel;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(String minLevel) {
		this.minLevel = minLevel;
	}
	public String getnPass() {
		return nPass;
	}
	public void setnPass(String nPass) {
		this.nPass = nPass;
	}
	public String getrPass() {
		return rPass;
	}
	public void setrPass(String rPass) {
		this.rPass = rPass;
	}
	public String getP_end() {
		return p_end;
	}
	public void setP_end(String p_end) {
		this.p_end = p_end;
	}
	public String getNickname1() {
		return nickname1;
	}
	public void setNickname1(String nickname1) {
		this.nickname1 = nickname1;
	}
	public String getU_like() {
		return u_like;
	}
	public void setU_like(String u_like) {
		this.u_like = u_like;
	}
	public String getU_hate() {
		return u_hate;
	}
	public void setU_hate(String u_hate) {
		this.u_hate = u_hate;
	}
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}
	public String getSuserId() {
		return suserId;
	}
	public void setSuserId(String suserId) {
		this.suserId = suserId;
	}

	
	
}
