package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.beans.PostBean;

public interface PostMapper {
	//메인페이지 게시물 노출
	@Select("SELECT post.*, user.nickname1, user.u_like, user.u_hate FROM post LEFT JOIN user ON post.userId = user.userId ORDER BY post.p_id DESC LIMIT 50")
	public List<PostBean> getPost(PostBean postBean);
	
	//메인페이지 게시물 노출 (1~3레벨)
	@Select("SELECT post.*, user.nickname1, user.u_like, user.u_hate FROM post LEFT JOIN user ON post.userId = user.userId WHERE raidLevel in (1, 3) ORDER BY post.p_id DESC LIMIT 50")
	public List<PostBean> getPost3(PostBean postBean);
	
	//메인페이지 게시물 노출 (5레벨)
	@Select("SELECT post.*, user.nickname1, user.u_like, user.u_hate FROM post LEFT JOIN user ON post.userId = user.userId WHERE raidLevel = 5 ORDER BY post.p_id DESC LIMIT 50")
	public List<PostBean> getPost5(PostBean postBean);
	
	//메인페이지 게시물 노출 (메가)
	@Select("SELECT post.*, user.nickname1, user.u_like, user.u_hate FROM post LEFT JOIN user ON post.userId = user.userId WHERE raidLevel = 'mega' ORDER BY post.p_id DESC LIMIT 50")
	public List<PostBean> getPostM(PostBean postBean);
	
	//메인페이지 게시물 노출 p_end 0인거
	@Select("SELECT post.*, user.nickname1, user.u_like, user.u_hate FROM post LEFT JOIN user ON post.userId = user.userId WHERE post.p_end = 0 ORDER BY post.p_id DESC LIMIT 50")
	public List<PostBean> get0Post(PostBean postBean);
	
	//메인페이지 게시물 노출 (1~3레벨) p_end 0인거
	@Select("SELECT post.*, user.nickname1, user.u_like, user.u_hate FROM post LEFT JOIN user ON post.userId = user.userId WHERE post.raidLevel in (1, 3) and post.p_end = 0 ORDER BY post.p_id DESC LIMIT 50")
	public List<PostBean> get0Post3(PostBean postBean);
	
	//메인페이지 게시물 노출 (5레벨) p_end 0인거
	@Select("SELECT post.*, user.nickname1, user.u_like, user.u_hate FROM post LEFT JOIN user ON post.userId = user.userId WHERE post.raidLevel = 5 and post.p_end = 0 ORDER BY post.p_id DESC LIMIT 50")
	public List<PostBean> get0Post5(PostBean postBean);
	
	//메인페이지 게시물 노출 (메가) p_end 0인거
	@Select("SELECT post.*, user.nickname1, user.u_like, user.u_hate FROM post LEFT JOIN user ON post.userId = user.userId WHERE post.raidLevel = 'mega' and post.p_end = 0 ORDER BY post.p_id DESC LIMIT 50")
	public List<PostBean> get0PostM(PostBean postBean);
	
	//마이포스트
	@Select("SELECT post.*, user.nickname1, user.u_like, user.u_hate FROM post LEFT JOIN user ON post.userId = user.userId "
			+ "WHERE post.userId=#{userId} and post.p_end in (0, 1) ORDER BY post.p_id DESC LIMIT 1")
	public List<PostBean> getMyPost(PostBean postBean);
	
	//마이포스트2
	@Select("SELECT post.*, user.nickname1, user.u_like, user.u_hate FROM post LEFT JOIN user ON post.userId = user.userId "
			+ "WHERE post.p_id=#{p_id}")
	public List<PostBean> getMyPost2(PostBean postBean);
	
	//게시글 작성
	@Insert("INSERT INTO post(userId, pokemon, raidLevel, startTime, endTime, nPass, rPass, minLevel) "
			+ "VALUES(#{userId}, #{pokemon}, #{raidLevel}, #{startTime}, #{endTime}, #{nPass}, #{rPass}, #{minLevel})")
	public void posting(PostBean postBean);
	
	//게시글 채택
	@Update("UPDATE post SET p_end=1, chat=#{chat} WHERE p_id=#{p_id}")
	public void pEnd1(PostBean postBean);
	
	//게시글 종료
	@Update("UPDATE post SET p_end=2, chat=null WHERE p_id=#{p_id}")
	public void pEnd2(PostBean postBean);
	
	//게시글 작성한게 있는지
	@Select("SELECT * FROM post WHERE userId=#{userId} ORDER BY p_id DESC LIMIT 1")
	public List<PostBean> checkEnd(PostBean postBean);
	
	//채팅생성체크
	@Select("SELECT * FROM post WHERE chat=#{chat}")
	public List<PostBean> checkChat(PostBean postBean);
}
