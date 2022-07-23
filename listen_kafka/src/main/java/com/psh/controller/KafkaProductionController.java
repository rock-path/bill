package com.psh.controller;


import cn.hutool.json.JSONUtil;
import com.psh.entity.Users;
import com.psh.hik.domain.BaseResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/production")
public class KafkaProductionController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.name}")
    private String topicName;

    @PostMapping("/send")
    public BaseResultModel send(Users users){
        System.out.println("生产者发送数据，topic为："+topicName);
        kafkaTemplate.send(topicName, JSONUtil.toJsonStr(users));
        return BaseResultModel.success();
    }


    @PostMapping("/sendList")
    public BaseResultModel sendList(Users users){
        System.out.println("生产者批量发送数据，topic为："+topicName);
        for (int i =0 ;i<100;i++){
            kafkaTemplate.send(topicName, JSONUtil.toJsonStr(users));
        }
        return BaseResultModel.success();
    }

}


