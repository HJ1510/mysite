package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bitacademy.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;			
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Handler Method에 @Auth를 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Handler Method에 @Auth가 없다면
		if(auth == null) {
			return true;
		}
		
		// 5. @Auth가 붙어있기때문에 인증(Authentification) 여부 확인
		HttpSession session = request.getSession();
		
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
			if (authUser == null ) {
				response.sendRedirect(request.getContextPath()+"/user/login");
				return false;
			}
			
		// 6. role(권한) 체크하기 ("user" or "admin") user로 들어오면 메인으로...???
		String role = auth.role();
		String authUserRole = authUser.getRole(); 
		System.out.println(authUserRole);
		System.out.println(role);
		if(!authUserRole.equals(role)) {
			response.sendRedirect(request.getContextPath());
			return false;
		}	

		// 7. @Auth도 붙어 있고 인증도 되어있고 권한도 있음
		return true;
	}

}
