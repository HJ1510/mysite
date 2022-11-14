package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.dao.BoardDao;
import com.bitacademy.mysite.vo.BoardVo;



public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("a");

		if ("writeform".equals(action)) {			
			request.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(request, response);	
		} else if("write".equals(action)){
			String title=request.getParameter("title");
			String content=request.getParameter("content");			
			String userNo=request.getParameter("no");
			
			BoardVo vo= new BoardVo();
			vo.setTitle(title);
			vo.setContents(content);
			vo.setUserNo(Long.parseLong(userNo));
			
			new BoardDao().insert(vo);
			
			response.sendRedirect(request.getContextPath() + "/board");
					
		} else if("view".equals(action)) { // 완료
		
			String no = request.getParameter("no");			
			BoardVo vo = new BoardDao().findByNo(Long.parseLong(no));
			request.setAttribute("boardVo", vo);
			
			request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);	
			
		} else if("modifyform".equals(action)) {	
			
			request.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(request, response);
		
		} else if("modify".equals(action)) {
		
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			String no=request.getParameter("no");
			
//			System.out.println("-"+title);
//			System.out.println("-"+content);
//			System.out.println("-"+no);
						
			BoardVo vo= new BoardVo();
			vo.setTitle(title);
			vo.setContents(content);
			vo.setNo(Long.parseLong(no));

			new BoardDao().update(vo);
			
			// userController update-updateform session 연구
			
			response.sendRedirect(request.getContextPath() + "/board");
						
		} else if("delete".equals(action)) { // 완료
			String sno = request.getParameter("no");
			Long no = Long.parseLong(sno);
			new BoardDao().deleteByNo(no);
			
			response.sendRedirect(request.getContextPath() + "/board");
		
		} else { // 완료		
			List<BoardVo> list = new BoardDao().findAll();

			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
