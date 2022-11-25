package com.bitacademy.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;

@Service
public class BoardService {
	private static final int LIST_SIZE = 10; // 페이지당 게시물 수
	private static final int PAGE_SIZE = 2; // index 하단에 표시되는 페이지 수
		
	@Autowired
	private BoardRepository boardRepository;
	
	
//	public List<BoardVo> getContentsList(){
//		return boardRepository.findAll();
//	}
	
	public Map<String, Object> getContentsList(int currentPage){
		// 리스트 가져오기
		boardRepository.findAll();
		// view의 페이징을 처리하기 위한 데이터의 값 계산
		int beginPage=0;
		int endPage=0;
		
		return null;
	}
	
	public void addContents(BoardVo vo) {
		boardRepository.insert(vo);
//		System.out.println("2:"+vo);
		//답글여부 확인해서 처리 groupNo==null
	}
	
	public BoardVo getContents(Long no) {	//view 완
		BoardVo boardVo = boardRepository.findByNo(no);
		
		if(boardVo != null) {
			boardRepository.hitCountUp(no);
		}
//		System.out.println("2:"+boardVo);
		return boardVo; //hit++
	}
		
	
//	public void deleteContents(Long no) {
//		boardRepository.deleteByNo(no);
//	}
	
	public void deleteContents(Long no, Long userNo) {
		if(userNo == null) {
			return;
		}		
		boardRepository.deleteByNoAndUserNo(no, userNo);
	}
	
	public void updateContents(BoardVo vo) {
		boardRepository.update(vo);
//		System.out.println("2:"+vo);		
	}

	public BoardVo findContentsForReply(Long no) {	
		return boardRepository.findByNoForReply(no);
	}
	
	public void replyContents(BoardVo vo) {
		boardRepository.replyInsert(vo);		
	}

}
