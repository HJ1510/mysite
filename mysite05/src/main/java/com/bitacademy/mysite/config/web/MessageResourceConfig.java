package com.bitacademy.mysite.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageResourceConfig {

	@Bean
	public MessageSource messageSource() { //mysite03 spring-servlet.xml 의 bean id와 동일해야..
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("com/bitacademy/mysite/config/web/messages_ko");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
	
	
}
