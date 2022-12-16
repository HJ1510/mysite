package com.bitacademy.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitacademy.mysite.service.GuestbookService;
import com.bitacademy.mysite.vo.GuestbookVo;

@Controller("guestbookControllerApi")
@RequestMapping("/guestbook/api")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list/{no}")
	public List <GuestbookVo> list(@PathVariable("no") Long startNo) {
		return guestbookService.getContentsList(startNo);
	}
	
}
