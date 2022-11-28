package com.bitacademy.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;

@Service
public class BoardService {
	private static final int LIST_SIZE = 5; // 페이지당 게시물 수, 표시되는 페이지 수
	private static final int PAGE_SIZE = 5; // index 하단에 표시되는 페이지가 넘어가는 단위
		
	@Autowired
	private BoardRepository boardRepository;
	
	
//	public List<BoardVo> getContentsList(){
//		return boardRepository.findAll();
//	}
	
	public Map<String, Object> getContentsList(int currentPage, String keyword){
		
		// view의 페이징을 처리하기 위한 데이터의 값 계산
		// 1. 페이징을 위한 기본 데이터 계산
		int totalCount = boardRepository.getWholeCount(keyword);
		int pageCount = (int) Math.ceil((double) totalCount / LIST_SIZE);
		int blockCount = (int) Math.ceil((double) pageCount / PAGE_SIZE);
		int currentBlock = (int) Math.ceil((double) currentPage / PAGE_SIZE);
		// 2. 파라미터 page 값 검증
		if (currentPage > pageCount) {
			currentPage = pageCount;
			currentBlock = (int) Math.ceil((double) currentPage / PAGE_SIZE);
		}

		if (currentPage < 1) {
			currentPage = 1;
			currentBlock = 1;
		}
		// 3. view에서 페이지 리스트를 렌더링 하기위한 데이터 값 계산
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1) * PAGE_SIZE + 1;
		int prevPage = (currentBlock > 1) ? (currentBlock - 1) * PAGE_SIZE : 0;
		int nextPage = (currentBlock < blockCount) ? currentBlock * PAGE_SIZE + 1 : 0;
		int endPage = (nextPage > 0) ? (beginPage - 1) + LIST_SIZE : pageCount;

		// 리스트 가져오기
		List<BoardVo> list =boardRepository.findAllByPageAndKeyword(currentPage, keyword, LIST_SIZE);
		
		// 리스트->맵
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("currentPage", currentPage);
		map.put("keyword", keyword);		
		map.put("listSize", LIST_SIZE);
		map.put("totalCount", totalCount);
		map.put("beginPage", beginPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("endPage", endPage);
		
	
		return map;
	}
	
	public BoardVo getContents(Long no) {	//view 완
		BoardVo boardVo = boardRepository.findByNo(no);
		
		if(boardVo != null) {
			boardRepository.hitCountUp(no);
		}
//		System.out.println("2:"+boardVo);
		return boardVo; //hit++
	}
	
	public BoardVo getContents(Long no, Long userNo) {
		BoardVo boardVo = boardRepository.findByNoAndUserNo(no, userNo);
		return boardVo;
	}
	
//	public void deleteContents(Long no) {
//		boardRepository.deleteByNo(no);
//	}
	
	public void deleteContents(Long boardNo, Long userNo) {
		boardRepository.delete(boardNo, userNo);		
	}
	
	public void addContents(BoardVo boardVo) {
		if (boardVo.getGroupNo() != null) {
			increaseGroupOrderNo(boardVo);
		}
		
		boardRepository.insert(boardVo);
//		System.out.println("2:"+vo);
		//답글여부 확인해서 처리 groupNo==null
	}
	
	private boolean increaseGroupOrderNo(BoardVo boardVo) {
		return boardRepository.updateOrderNo(boardVo.getGroupNo(), boardVo.getOrderNo()) > 0;
	}

	public void updateContents(BoardVo vo) {
		boardRepository.update(vo);
//		System.out.println("2:"+vo);		
	}

//	public BoardVo findContentsForReply(Long no) {	
//		return boardRepository.findByNoForReply(no);
//	}
//	
//	public void replyContents(BoardVo vo) {
//		boardRepository.replyInsert(vo);		
//	}



}
