package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.beans.CommentBean;

public interface CommentMapper {
	//댓글작성
	@Insert("INSERT INTO comment(p_id, userId, checkNum) VALUES(#{p_id}, #{userId}, #{checkNum})")
	public void commenting(CommentBean commentBean);
	
	//댓글꺼내기
	@Select("SELECT comment.*, user.* FROM comment LEFT JOIN user ON comment.userId = user.userId WHERE comment.p_id=#{p_id}")
	public List<CommentBean> getComment(CommentBean commentBean);
	
	//댓글채택1
	@Update("UPDATE comment SET c_end=1 WHERE c_id=#{c_id}")
	public void changeComment1(CommentBean commentBean);
	
	//댓글채택탈락
	@Update("UPDATE comment SET c_end=2 WHERE p_id=#{p_id}")
	public void changeComment2(CommentBean commentBean);
	
	//댓글삭제
	@Delete("DELETE FROM comment WHERE c_id=#{c_id}")
	public void commentDel(CommentBean commentBean);
	
	//게시글 작성한게 있는지
	@Select("SELECT * FROM comment WHERE userId=#{userId} ORDER BY c_id DESC LIMIT 1")
	public List<CommentBean> checkEnd(CommentBean commentBean);
}
