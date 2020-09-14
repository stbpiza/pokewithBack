package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.exam.beans.PostBean;

public interface PostMapper {
	@Select("SELECT * FROM post ORDER BY p_id DESC LIMIT 10")
	public List<PostBean> getPost(PostBean postBean);
	
	@Insert("INSERT INTO post(userId, pokemon, raidLevel, startTime, endTime, nPass, rPass, minLevel) "
			+ "VALUES(#{userId}, #{pokemon}, #{raidLevel}, #{startTime}, #{endTime}, #{nPass}, #{rPass}, #{minLevel})")
	public void posting(PostBean postBean);
}
