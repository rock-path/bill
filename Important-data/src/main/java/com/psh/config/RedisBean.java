package com.psh.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
//过滤器中注入redisTemplate
public class RedisBean {

    @Autowired
    private RedisTemplate<String,Object>  redisTemplate;

    public static RedisTemplate redis;


    @PostConstruct
    public void getRedisTemplate(){
        redis=this.redisTemplate;
    }

}



