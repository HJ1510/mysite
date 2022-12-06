package com.bitacademy.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice //controller에 AOP 적용 // spring-servlet.xml에 경로 설정 추가해야함
public class ControllerExceptionHandler {
	
	private static final Log Logger = LogFactory.getLog(ControllerExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public String HandlerException(Model model, Exception e) { 
		// 404 Error
		if(e instanceof NoHandlerFoundException) {
			return "error/404";
		}
		
		// 500 Error
		// 1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors.toString()); // 실제로는 화면출력x 로그파일을 저장해야함
		Logger.error(errors.toString());
		
		
		// 2. 사과페이지(HTML 응답, 정상종료)
		model.addAttribute("exception", errors.toString());
		return "error/exception";
	}
}
