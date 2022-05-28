package com.psh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
@EnableAsync
@EnableScheduling
public class ImportantDataApp {
    public static void main(String[] args) {
        SpringApplication.run(ImportantDataApp.class);
    }
}
