package com.demo.netty.service;

import com.demo.netty.domain.MsgAgreement;
import com.demo.netty.redis.Publisher;
import com.demo.netty.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjianhua
 * @Description 拓展服务
 * @date 2021/8/13 14:08
 */
@Service
public class ExtServerService {

    @Autowired
    private Publisher publisher;

    @Autowired
    private RedisUtil redisUtil;

    public void push(MsgAgreement msgAgreement){
        publisher.pushMessage("netty-demo-push-msgAgreement",msgAgreement);
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }
}
