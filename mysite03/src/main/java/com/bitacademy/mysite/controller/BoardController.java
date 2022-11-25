package com.bitacademy.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.mysite.security.Auth;
import com.bitacademy.mysite.service.BoardService;
import com.bitacademy.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping({"/list", ""}) // 임시완료
//	public String list(Model model) {
//		model.addAttribute("list", boardService.getContentsList());
//		return "board/list";		
//	}
	
	public String list(@RequestParam(value="p", required=true, defaultValue="1") Integer page, @RequestParam(value="kwd", required=true, defaultValue="") String keyword, Model model){
		boardService.getContentsList(0);
		
		return "board/list";
	}
	
	@Auth
	@RequestMapping(value = {"/write/{no}", "/write"}, method = RequestMethod.GET) // 완료
	public String write(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		return "board/write";
	}
	
	@RequestMapping(value = "/write/{no}", method = RequestMethod.POST) // 완료
	public String write(@PathVariable("no") Long no, BoardVo vo) {
		vo.setUserNo(no);
//		System.out.println("1:"+vo);
		boardService.addContents(vo);
//		System.out.println("no:"+vo.getNo());
//		System.out.println("uno:"+vo.getUserNo());
		return "redirect:/board";
	}
	
	@RequestMapping("/view/{no}") // 완
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo boardVo = boardService.getContents(no);
		model.addAttribute("boardVo", boardVo);	
		return "board/view";
	}
	
	@Auth
	@RequestMapping({"/delete/{no}"})
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
		BoardVo boardVo = boardService.getContents(no);
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
	@RequestMapping(value = {"/reply/{no}/{userNo}", "/reply"}, method = RequestMethod.GET)
	public String reply(@PathVariable("no") Long no, BoardVo vo, Model model) {
		BoardVo boardVo = boardService.findContentsForReply(no);
		model.addAttribute("title", boardVo.getTitle()); 
		model.addAttribute("contents", boardVo.getContents()); 
		model.addAttribute("groupNo", boardVo.getGroupNo()); 
		model.addAttribute("orderNo", boardVo.getOrderNo()); 
		model.addAttribute("depth", boardVo.getDepth()); 
		System.out.println(boardVo);
		return "board/reply";
	}
	
	@Auth
	@RequestMapping(value = {"/reply/{no}/{userNo}"}, method = RequestMethod.POST)
	public String reply(@PathVariable("no") Long no, @PathVariable("userNo") Long userNo, BoardVo vo) {
		vo.setUserNo(userNo);
		boardService.replyContents(vo);
		System.out.println(vo);
		return "redirect:/board";
	}
}
