package com.psh.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    private static final Logger log = LoggerFactory.getLogger(MybatisPlusConfig.class);

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        log.info("开启分页插件....");
        return (new PaginationInterceptor()).setCountSqlParser(new JsqlParserCountOptimize(true));
    }

    @Bean
    public GlobalConfig globalConfig(MyMetaObjectHandler myMetaObjectHandler) {
        log.debug("开启参数自动注入插件....");
        GlobalConfig globalConfig = new GlobalConfig();
        return globalConfig.setMetaObjectHandler(myMetaObjectHandler);
    }

    @ConditionalOnMissingBean({MetaObjectHandler.class})
    @Bean
    public MyMetaObjectHandler myMetaObjectHandler() {
        return new MyMetaObjectHandler();
    }
}
