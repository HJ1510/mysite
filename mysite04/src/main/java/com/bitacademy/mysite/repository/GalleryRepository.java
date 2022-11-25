package com.bitacademy.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> findAll() {
		return sqlSession.selectList("gallery.findAll");
	}
	
	public void insert(GalleryVo galleryVo) {
		sqlSession.insert("gallery.insert", galleryVo);
//		System.out.println("3"+galleryVo);
	}

	public GalleryVo deleteByNo(Long no) {
//		System.out.println("rep"+no);
		return sqlSession.selectOne("gallery.deleteByNo", no);
	}


}
