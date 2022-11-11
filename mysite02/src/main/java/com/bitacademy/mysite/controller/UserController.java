package com.bitacademy.mysite.controller;

import java.io.IOException;

//import javax.servlet.RequestDispatcher; // ?
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.dao.UserDao;
import com.bitacademy.mysite.vo.UserVo;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String action = request.getParameter("a");

		if ("joinform".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp").forward(request, response);
		} else if ("join".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVo vo = new UserVo();
			vo.setName(name);
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setGender(gender);

			new UserDao().insert(vo);

			response.sendRedirect(request.getContextPath() + "/user?a=joinsuccess");
		} else if ("joinsuccess".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp").forward(request, response);
		} else if ("updateform".equals(action)) {
			//// Access Control
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			if (authUser == null) {
				response.sendRedirect(request.getContextPath() + "/user?a=loginform");
				return;
			}
			////
			UserVo vo = new UserDao().findByNo(authUser.getNo());
			request.setAttribute("userVo", vo);

			request.getRequestDispatcher("/WEB-INF/views/user/updateform.jsp").forward(request, response);
		} else if ("update".equals(action)) {
			//// Access Control
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			if (authUser == null) {
				response.sendRedirect(request.getContextPath() + "/user?a=loginform");
				return;
			}
			////

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVo vo = new UserVo();
			vo.setNo(authUser.getNo());
			vo.setName(name);
			vo.setPassword(password);
			vo.setGender(gender);

			// updatedb
			new UserDao().update(vo);

			// update
			authUser.setName(name);
			response.sendRedirect(request.getContextPath() + "/user?a=updateform");


		} else if ("loginform".equals(action)) {
			request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp").forward(request, response);
		} else if ("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			UserVo authUser = new UserDao().findByEmailAndPassword(email, password);

			if (authUser == null) {
				/* 인증실패 */
				request.setAttribute("email", email);
				request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp").forward(request, response);
				return;
			}

			/* 로그인처리 */
			HttpSession session = request.getSession(true); // request.getSession(true) 없으면 만들어서 반환 session 동일한
															// sessionid에서 들어온 값을 모아둔..?
			session.setAttribute("authUser", authUser);

			response.sendRedirect(request.getContextPath());
			
		} else if("logout".equals(action)) {
			HttpSession session = request.getSession();
			if (session == null) {
				/* 로그인 안된 상황 */
				response.sendRedirect(request.getContextPath());
				return; // 코드 끝 지정! 없으면 오류 날 수 있음
			}
			session.removeAttribute("authUser"); // authUser가 지워짐
			session.invalidate(); // 세션 아이디 변경
			response.sendRedirect(request.getContextPath());
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
