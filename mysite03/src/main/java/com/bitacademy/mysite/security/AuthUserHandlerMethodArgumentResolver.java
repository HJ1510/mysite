package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bitacademy.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if (!supportsParameter(parameter)) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		if (session == null) {
			return null;		}
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) { // @AuthUser UserVo authUser
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @AuthUser가 안붙어있으면
		if(authUser == null) {
			return false;
		}
		
		// @AuthUser는 붙어있지만 파라미터타입이 UserVo가 아니라면
		if(!parameter.getParameterType().equals(UserVo.class)) {
			return false;
		}
		return true;
	}
}
