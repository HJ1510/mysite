package com.bitacademy.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) { // 프로그램 로딩
		ServletContext sc = sce.getServletContext();
		String contextConfigLocation = sc.getInitParameter("contextConfigLocation");
		System.out.println("Application Starts...." + contextConfigLocation);

	}

	public void contextDestroyed(ServletContextEvent sce) { // 프로그램 내려갈때

	}
}
