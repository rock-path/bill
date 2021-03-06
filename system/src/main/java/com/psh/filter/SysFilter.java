package com.psh.filter;


import cn.hutool.json.JSONUtil;
import com.psh.config.RedisBean;
import com.psh.entity.BillRequestUrl;
import com.psh.hik.common.Constant;
import lombok.val;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;
import java.util.logging.LogRecord;


public class SysFilter implements Filter {


    /**
     * 可以初始化Filter在web.xml里面配置的初始化参数
     * filter对象只会创建一次，init方法也只会执行一次。
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        StringBuffer requestURL = httpServletRequest.getRequestURL();
        if (requestURL.indexOf(".js") < 0 && requestURL.indexOf("doc.html") < 0 && requestURL.indexOf("swagger") < 0 && requestURL.indexOf("/v2/api-docs") < 0) {
            System.out.println(requestURL);
            RedisTemplate redisTemplate = RedisBean.redis;
            BillRequestUrl billRequestUrl = BillRequestUrl.build(UUID.randomUUID().toString(), requestURL.toString());
            redisTemplate.opsForHash().put(Constant.SYSTEMURL, billRequestUrl.getUuid(), JSONUtil.toJsonStr(billRequestUrl));
        }

//        ServletContext servletContext = request.getServletContext();
//        RedisTemplate redisTemplate = (RedisTemplate)WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("redisTemplate");

        try {
            //过滤逻辑
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void destroy() {

    }
}
