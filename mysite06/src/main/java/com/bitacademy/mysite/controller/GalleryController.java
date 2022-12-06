package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.mysite.security.Auth;
import com.bitacademy.mysite.service.FileUploadService;
import com.bitacademy.mysite.service.GalleryService;
import com.bitacademy.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String list(Model model) {
		List<GalleryVo> list  = galleryService.getImageList();
		model.addAttribute("list", list);
//		System.out.println(list);
		return "gallery/index";
	}
	
	@Auth
	@RequestMapping("/upload") // 업로드는 로그인된 사용자 모두
	public String upload(
//			@RequestParam("comments") String comments, 
			@RequestParam("file") MultipartFile multipartFile,
			GalleryVo galleryVo) {
//		System.out.println(comments);
		String url = fileUploadService.restore(multipartFile);
		galleryVo.setUrl(url);
		galleryService.saveImages(galleryVo);
//		System.out.println("setUrl:"+url);
//		System.out.println(galleryVo);
		return "redirect:/gallery";
	}
		
	@Auth(role="admin") // 삭제는 admin 권한 있는 경우만
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
//		System.out.println(no);
		galleryService.removeImages(no);
		return "redirect:/gallery";
	}


}
