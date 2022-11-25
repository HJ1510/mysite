package com.bitacademy.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean update(BoardVo vo) {
		int count = sqlSession.update("board.update", vo);
//		System.out.println("3:"+vo);
		return count == 1;
	}

//	public Boolean deleteByNo(Long no) {
//		int count = sqlSession.delete("board.deleteByNo", no);
//		return count == 1;
//	}
	
	public Boolean deleteByNoAndUserNo(Long no, Long userNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("userNo", userNo);		
		return sqlSession.selectOne("board.deleteByNoAndUserNo", map);
		
	}
	
	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}
	
	public Boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;
	}
	
	public BoardVo findByNo(Long no) { //view 완
//		System.out.println("3:"+no);
		return sqlSession.selectOne("board.findByNo", no);
	}

	public void hitCountUp(Long no) {
		sqlSession.update("board.hitCountUp", no);	
	}

	public BoardVo findByNoForReply(Long no) {
		return sqlSession.selectOne("board.findByNoForReply", no);
	}
	
	public Boolean replyInsert(BoardVo vo) {
		int count = sqlSession.insert("board.replyInsert", vo);
		return count == 1;		
	}

}
