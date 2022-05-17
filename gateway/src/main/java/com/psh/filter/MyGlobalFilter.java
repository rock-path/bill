package com.psh.filter;

import com.psh.hik.common.Constant;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.UUID;

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    //全局过滤器
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        System.out.println(uri);
        redisTemplate.opsForHash().put(Constant.GATEWAYURL, UUID.randomUUID(),uri);
        String username = exchange.getRequest().getQueryParams().getFirst("username");
//        if (username != null && username.trim().length() >= 0) {
//            if (username.equals("admin")) {
//                System.out.println("验证通过");
//                return chain.filter(exchange);
//            } else {
//                System.out.println("验证失败");
//                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//                return exchange.getResponse().setComplete();
//            }
//        } else {
//            System.out.println("用户名不存在");
//            //403拒绝访问
//            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
////            return chain.filter(exchange);
//            return  null;
//        }

        System.out.println("暂时不做校验");
        return chain.filter(exchange);
    }

    /**
     * 过滤器排序
     * @return 数值越小 越先执行
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
