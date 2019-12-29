package com.list.customer2.customer2client.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.list.customer2.customer2client.aop")
@EnableAspectJAutoProxy
public class SpringConfiguration {
}
