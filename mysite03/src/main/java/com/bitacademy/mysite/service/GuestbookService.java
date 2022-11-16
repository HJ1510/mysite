package com.bitacademy.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.GuestbookRepository;
import com.bitacademy.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVo> getContents(){
		return guestbookRepository.findAll();
	}
	
	public void deleteContents(Long no, String password){
//		System.out.println(no+"|2|2|"+password);
		guestbookRepository.deleteByNoAndPassword(no, password);	
	}
	
	public void addContents(GuestbookVo vo){
		guestbookRepository.insert(vo);
		
	}
}
