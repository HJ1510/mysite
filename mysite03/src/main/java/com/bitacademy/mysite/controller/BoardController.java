package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping({"/list", ""})
	public String list(Model model) {
		model.addAttribute("list", boardService.getContents());
		return "board/list";
		
	}
	
//	public Map<String, Object> findContentsList(int currentPage){
//		// 리스트 가져오기
//		
//		// view의 페이징을 처리하기 위한 데이터의 값 계산
//		int beginPage=0;
//		int endPage=0;
//		
//		return null;
//	}
//	
	@RequestMapping("/write")
	public String write() {
		return "board/write";
	}
	
}
