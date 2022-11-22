package com.bitacademy.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(GalleryVo galleryVo) {
		int count = sqlSession.insert("gallery.insert", galleryVo);
//		System.out.println("3"+galleryVo);
		return count == 1;
	}

	public GalleryVo delete(Long no) {
		
		return sqlSession.selectOne("gallery.deleteByNo", no);
	}

}
