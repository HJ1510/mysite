package com.bitacademy.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice //controller에 AOP 적용 // spring-servlet.xml에 경로 설정 추가해야함
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String HandlerException(Model model, Exception e) { 
		// 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors.toString()); // 실제로는 화면출력x 로그파일을 저장해야함
		
		// 사과페이지(HTML 응답, 정상종료)
		model.addAttribute("exception", errors.toString());
		return "error/exception";
	}
}
