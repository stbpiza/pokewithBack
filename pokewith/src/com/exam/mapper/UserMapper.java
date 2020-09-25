package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.beans.UserBean;

public interface UserMapper {

	//유저정보출력
	@Select("SELECT * FROM user WHERE userId=#{userId}")
	public List<UserBean> getUser(UserBean userBean);
	
	//신규유저등록
	@Insert("INSERT INTO user(userId, nickname1, friendCode1) VALUES(#{userId}, #{nickname1}, #{friendCode1})")
	public void signIn(UserBean userBean);
	
	//유저정보수정
	@Update("UPDATE user SET nickname1=#{nickname1}, friendCode1=#{friendCode1}, nickname2=#{nickname2}, friendCode2=#{friendCode2}, "
			+ "nickname3=#{nickname3}, friendCode3=#{friendCode3}, nickname4=#{nickname4}, friendCode4=#{friendCode4}, "
			+ "nickname5=#{nickname5}, friendCode5=#{friendCode5} WHERE userId=#{userId}")
	public void reUser(UserBean userBean);
	

}