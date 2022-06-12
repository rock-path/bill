package com.psh.monitor;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author zsx
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {


    /**
     * Creates new {@link } for {@code __keyevent@*__:expired} messages.
     *
     * @param listenerContainer must not be {@literal null}.
     */
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 这地方可以做自己的业务处理,message.toString()可以获取失效的key
        String expiredKey = message.toString();
        System.out.println("过期redis_key" + expiredKey);
    }
}