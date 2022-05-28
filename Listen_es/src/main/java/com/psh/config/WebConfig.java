package com.psh.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    //注册过滤器
//    @Bean
//    public FilterRegistrationBean testFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean(new SysFilter());
//        registration.addUrlPatterns("/*"); //
//        registration.setName("SysFilter");
//        return registration;
//    }

}
