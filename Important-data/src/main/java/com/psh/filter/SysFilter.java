package com.psh.filter;



import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;


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
//            RedisTemplate redisTemplate = RedisBean.redis;
//            BillRequestUrl billRequestUrl = BillRequestUrl.build(UUID.randomUUID().toString(), requestURL.toString());
//            redisTemplate.opsForHash().put(Constant.SYSTEMURL, billRequestUrl.getUuid(), JSONUtil.toJsonStr(billRequestUrl));
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
