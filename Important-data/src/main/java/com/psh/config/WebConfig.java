package com.psh.config;

import com.psh.filter.SysFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new SysFilter());
        registration.addUrlPatterns("/*"); //
        registration.setName("SysFilter");
        return registration;
    }

}
