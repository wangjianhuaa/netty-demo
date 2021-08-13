package com.demo.netty.redis;

import com.demo.netty.domain.MsgAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 13:48
 */
@Service
public class Publisher {

    private final RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public Publisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void pushMessage(String topic, MsgAgreement message){
        redisTemplate.convertAndSend(topic,message);
    }
}
