package com.bitacademy.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.bitacademy.mysite.config.web.FileuploadConfig;
import com.bitacademy.mysite.config.web.MVCConfig;
import com.bitacademy.mysite.config.web.MessageResourceConfig;
import com.bitacademy.mysite.config.web.SecurityConfig;



@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.bitacademy.mysite.controller","com.bitacademy.mysite.exception"})
@Import({MVCConfig.class, SecurityConfig.class, FileuploadConfig.class, MessageResourceConfig.class})
public class WebConfig {
}
