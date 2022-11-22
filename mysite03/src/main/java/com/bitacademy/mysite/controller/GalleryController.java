package com.bitacademy.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.mysite.service.FileUploadService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	
	@RequestMapping("")
	public String index() {
		return "gallery/index";
	}
	
	@RequestMapping("upload")
	public String upload() {
		fileUploadService.restore(null);
		galleryService.saveImages(galleryVo);
		return "redirct:/gallery";
	}
	
	@RequestMapping("delete/{no}")
	public String delete() {
		galleryService.removeImages(no);
		return "redirct:/gallery";
	}


}
