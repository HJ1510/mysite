package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bitacademy.mysite.service.UserService;
import com.bitacademy.mysite.vo.UserVo;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
//		UserService userService=new UserService(); // 안됨 이 내용은 null
		UserVo authUser = userService.findUser(email, password);
		
		if(authUser == null) {
			request.setAttribute("email", email);
			return "user/login";
		} 
		
		session.setAttribute("authUser", authUser);
		return "redirect:/";

		return false;
	}

}