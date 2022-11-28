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
	
	public List<BoardVo> findAllByPageAndKeyword(Integer page, String keyword, Integer listSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("listSize", listSize);
		map.put("startPage", (page-1) * listSize);
		return sqlSession.selectList("board.findAllByPageAndKeyword", map);
	}
	
//	public List<BoardVo> findAll() {
//		return sqlSession.selectList("board.findAll");
//	}
	
	public int getWholeCount(String keyword) {		
		return sqlSession.selectOne("board.wholeCount", keyword);
	}

	public BoardVo findByNo(Long no) { //view 완
//		System.out.println("3:"+no);
		return sqlSession.selectOne("board.findByNo", no);
	}
	
	public void hitCountUp(Long no) {
		sqlSession.update("board.hitCountUp", no);	
	}
	
	public boolean update(BoardVo vo) {
		int count = sqlSession.update("board.update", vo);
//		System.out.println("3:"+vo);
		return count == 1;
	}

//	public Boolean deleteByNo(Long no) {
//		int count = sqlSession.delete("board.deleteByNo", no);
//		return count == 1;
//	}
	
	public void delete(Long boardNo, Long userNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", boardNo);
		map.put("userNo", userNo);		
		sqlSession.selectOne("board.delete", map);
	}
	
	public Boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;
	}
	
	public int updateOrderNo(Integer groupNo, Integer orderNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		return sqlSession.update("board.updateOrederNo", map);
	}
	
	public BoardVo findByNoAndUserNo(Long no, Long userNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("no", no);
		map.put("userNo", userNo);
		return sqlSession.selectOne("board.findByNoAndUserNo", map);
	}

//	public BoardVo findByNoForReply(Long no) {
//		return sqlSession.selectOne("board.findByNoForReply", no);
//	}
//	
//	public Boolean replyInsert(BoardVo vo) {
//		int count = sqlSession.insert("board.replyInsert", vo);
//		return count == 1;		
//	}










}
