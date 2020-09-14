package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.beans.UserBean;

public interface UserMapper {

	@Select("SELECT * FROM user WHERE userId=#{userId}")
	public List<UserBean> getUser(UserBean userBean);
	
	@Insert("INSERT INTO user(userId, nickname1, friendCode1) VALUES(#{userId}, #{nickname1}, #{friendCode1})")
	public void signIn(UserBean userBean);
	
}
