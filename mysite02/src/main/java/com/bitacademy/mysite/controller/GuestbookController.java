package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.mysite.dao.GuestbookDao;
import com.bitacademy.mysite.vo.GuestbookVo;



public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("a");
		if ("insert".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String message = request.getParameter("content");

			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(message);
			new GuestbookDao().insert(vo);
			
			response.sendRedirect("/mysite02/guestbook");
		} else if ("deleteform".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(request, response);			
		} else if ("delete".equals(action)) {
			String sno = request.getParameter("no");
			Long no = Long.parseLong(sno);
			String password = request.getParameter("password");

			new GuestbookDao().deleteByNoAndPassword(no, password);
			
//			String no = request.getParameter("no");
//			String password = request.getParameter("password");
//			new GuestbookDao().deleteByNoAndPassword(Long.parseLong(no), password);

//			String password = request.getParameter("password");
//			new GuestbookDao().deleteByNoAndPassword(Long.parseLong(request.getParameter("no")), password);
			
			response.sendRedirect(request.getContextPath() + "/guestbook");
		} else {
			List<GuestbookVo> list = new GuestbookDao().findAll();

			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
			rd.forward(request, response);
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
