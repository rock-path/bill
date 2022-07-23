package com.psh.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.net.InetAddress;

@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {


    @Value("${es.port}")
    private int port;
    @Value("${es.host}")
    private InetAddress host;

    @Override
    @Bean(destroyMethod = "close")
    public RestHighLevelClient elasticsearchClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, port, "http")));
    }
}
