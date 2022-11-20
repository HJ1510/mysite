package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.service.BoardService;
import com.bitacademy.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping({"/list", ""}) // 임시완료
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
	
	@RequestMapping(value = "/write/{no}", method = RequestMethod.GET) // 완료
	public String write(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "board/write";
	}
	
	@RequestMapping(value = "/write/{no}", method = RequestMethod.POST) // 완료
	public String write(@PathVariable("no") Long no, BoardVo vo ) {
		vo.setUserNo(no);
//		System.out.println("1:"+vo);
		boardService.addContents(vo);
		return "redirect:/board";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.findContents(no);
		model.addAttribute("title", boardVo.getTitle());
		model.addAttribute("contents", boardVo.getContents());
//		model.addAttribute("no", no);
//		model.addAttribute("userNo", boardVo.getUserNo()); ???
		System.out.println("1:"+ model);
		return "board/view";
	}

}
