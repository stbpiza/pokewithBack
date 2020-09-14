package com.exam.beans;

public class PostBean {
	private String p_id;
	private String userId;
	private String pokemon;
	private String raidLevel;
	private String startTime;
	private String endTime;
	private String nPass;
	private String rPass;
	private String end;
	private String minLevel;
	
	
	@Override
	public String toString() {
		return "PostBean [p_id=" + p_id + ", userId=" + userId + ", pokemon=" + pokemon + ", raidLevel=" + raidLevel
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", nPass=" + nPass + ", rPass=" + rPass
				+ ", end=" + end + ", minLevel=" + minLevel + "]";
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
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(String minLevel) {
		this.minLevel = minLevel;
	}
	
	
}
