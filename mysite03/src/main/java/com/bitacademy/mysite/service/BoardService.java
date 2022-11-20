package com.bitacademy.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.BoardRepository;
import com.bitacademy.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> getContents(){
		return boardRepository.findAll();
	}
	
//	public Map<String, Object> findContentsList(int currentPage){
//		// 리스트 가져오기
//		boardRepository.findAll();
//		// view의 페이징을 처리하기 위한 데이터의 값 계산
//		int beginPage=0;
//		int endPage=0;
//		
//		return null;
//	}
	
	public void addContents(BoardVo vo) {
		boardRepository.insert(vo);
//		System.out.println("2:"+vo);
		//답글여부 확인해서 처리 groupNo==null
	}
	
	public BoardVo findContents(Long no) {
		boardRepository.findByNo(no);		
		System.out.println("2:"+no);
		return boardRepository.findByNo(no); //hit++
	}
	
	public BoardVo findContents(Long no, Long userNo) {
		return null;
	}

	
	public void updateContents(BoardVo vo) {
		
	}
	
	public void deleteContents(Long no, Long userNo) {
		
	}
}
