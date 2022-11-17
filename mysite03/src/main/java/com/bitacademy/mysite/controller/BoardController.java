package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value = "/write/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "board/write";
	}
	
}
