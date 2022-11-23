package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.security.Auth;
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
	@Auth
	@RequestMapping(value = {"/write/{no}", "/write"}, method = RequestMethod.GET) // 완료
	public String write(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "board/write";
	}
	
	@RequestMapping(value = "/write/{no}", method = RequestMethod.POST) // 완료
	public String write(@PathVariable("no") Long no, BoardVo vo ) {
		vo.setUserNo(no);
		System.out.println("1:"+vo);
		boardService.addContents(vo);
		System.out.println("no:"+vo.getNo());
		System.out.println("uno:"+vo.getUserNo());
		return "redirect:/board";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.findContents(no);
		model.addAttribute("title", boardVo.getTitle());
		model.addAttribute("contents", boardVo.getContents());
		model.addAttribute("userNo", boardVo.getUserNo()); 
		boardService.hitCountUp(no);
//		System.out.println("1:"+ model);		
//		System.out.println("1vo:"+ boardVo);		
		return "board/view";
	}
	
	@Auth
	@RequestMapping({"/delete/{no}/{userNo}", "/delete/{no}", "delete"})
//	public String delete(@PathVariable("no") Long no) {
//		boardService.deleteContents(no);
//		return "redirect:/board";
//	}
	
	public String delete(@PathVariable("no") Long no, @PathVariable(value="userNo", required=false) Long userNo) {
			boardService.deleteContents(no, userNo);		
		return "redirect:/board";
	}
	
	
	@Auth
	@RequestMapping(value = {"/modify/{no}", "/modify"}, method = RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.findContents(no);
		model.addAttribute("no", no);
		model.addAttribute("title", boardVo.getTitle());
		model.addAttribute("contents", boardVo.getContents());
//		System.out.println("mod:"+model);
		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value = {"/modify/{no}"}, method = RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, BoardVo vo) {
		boardService.updateContents(vo);
//		System.out.println("1:"+vo);
		return "redirect:/board/view/{no}";
	}
	
	@Auth
	@RequestMapping(value = {"/reply/{no}", "/reply"}, method = RequestMethod.GET)
	public String reply(Model model) {
		
		return "board/reply";
	}
	
	@Auth
	@RequestMapping(value = {"/reply/{no}"}, method = RequestMethod.POST)
	public String reply(BoardVo vo) {
		
		return "redirect:/board";
	}
}
