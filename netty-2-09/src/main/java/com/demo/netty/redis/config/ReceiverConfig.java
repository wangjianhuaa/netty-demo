package com.demo.netty.redis.config;

import com.demo.netty.redis.MsgAgreementReceiver;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author wangjianhua
 * @Description 订阅者配置
 * @date 2021/8/13 11:05
 */
@Configuration
public class ReceiverConfig {

    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter  msgAgreementListenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(msgAgreementListenerAdapter,new PatternTopic("netty-demo-push-msgAgreement"));
        return container;
    }

    public MessageListenerAdapter msgAgreementListenerAdapter(MsgAgreementReceiver receiver){
        //defaultListenerMethod 默认监听器消息 这里把自己覆盖的方法填进去
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }
}
