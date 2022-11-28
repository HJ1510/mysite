package com.bitacademy.mysite.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bitacademy.mysite.security.AuthInterceptor;
import com.bitacademy.mysite.security.AuthUserHandlerMethodArgumentResolver;
import com.bitacademy.mysite.security.LoginInterceptor;
import com.bitacademy.mysite.security.LogoutInterceptor;

@SpringBootConfiguration
@PropertySource("classpath:web/fileupload.properties") //addResourceHandlers
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;
	
	// Argument Resolvers
	@Bean
	public HandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(authUserHandlerMethodArgumentResolver());
	}
	
	
	// Security Interceptors
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(loginInterceptor())
			.addPathPatterns("/user/auth");
		registry
			.addInterceptor(logoutInterceptor())
			.addPathPatterns("/user/logout");
		registry
			.addInterceptor(authInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/user/auth")
			.excludePathPatterns("/user/logout")
			.excludePathPatterns("/assets/**");
	}
	
	// file upload 가상 url
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler(env.getProperty("fileupload.resourceMapping")+"/**")
			.addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation")+"/");
	}

}
