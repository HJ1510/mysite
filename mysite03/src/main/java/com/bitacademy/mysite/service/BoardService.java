package com.bitacademy.mysite.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.bitacademy.mysite.vo.BoardVo;

@Service
public class BoardService {

	public void addContents(BoardVo vo) {
		//답글여부 확인해서 처리 groupNo==null
	}
	
	public BoardVo findContents(Long no) {
		return null; //hit++
	}
	
	public BoardVo findContents(Long no, Long userNo) {
		return null;
	}
	
	public Map<String, Object> findContentsList(int currentPage){
		// 리스트 가져오기
		
		// view의 페이징을 처리하기 위한 데이터의 값 계산
		int beginPage=0;
		int endPage=0;
		
		return null;
	}
	
	public void updateContents(BoardVo vo) {
		
	}
	
	public void deleteContents(Long no, Long userNo) {
		
	}
}
