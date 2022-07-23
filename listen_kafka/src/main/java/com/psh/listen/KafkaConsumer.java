package com.psh.listen;

import cn.hutool.json.JSONUtil;
import com.psh.entity.Users;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {



    @Value("${kafka.name}")
    private String kafkaName;

    @KafkaListener(topics = "${kafka.name}",groupId = "tscm01")
    public void listenGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println("value"+value);
        System.out.println("record:"+record);
        Users users = JSONUtil.toBean(value,Users.class);
        System.out.println("users"+users);
        //⼿动提交offset,如果不提交会导致消息重复消费
        //ack只有关闭自动提交才生效
        ack.acknowledge();
    }
}
