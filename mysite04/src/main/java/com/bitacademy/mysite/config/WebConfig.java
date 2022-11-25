package com.bitacademy.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;



@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.bitacademy.mysite.controller","com.bitacademy.mysite.exception"})
@Import({})
public class WebConfig {
}
