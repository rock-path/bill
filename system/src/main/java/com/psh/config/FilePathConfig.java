package com.psh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilePathConfig implements WebMvcConfigurer {

    //"file:///H:/upload/3.png"
    private final  String url= "file:/H:/upload/";


    private final  String urlLinux= "file:/usr/local/webData/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/seeFile/**") //虚拟url路径
                .addResourceLocations(urlLinux); //真实本地路径
    }
}
