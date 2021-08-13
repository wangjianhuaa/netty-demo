package com.demo.netty.redis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author wangjianhua
 * @Description 发布者设置
 * @date 2021/8/13 11:00
 */
@Configuration
public class PublisherConfig {

    @Bean
    public RedisTemplate<String,Object> redisMessageTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer<>(Object.class));
        return redisTemplate;
    }
}
