package com.demo.netty.redis;

import com.alibaba.fastjson.JSON;
import com.demo.netty.domain.MsgAgreement;
import com.demo.netty.util.CacheUtil;
import com.demo.netty.util.MsgUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangjianhua
 * @Description 接收订阅到的消息并进行业务处理
 * @date 2021/8/13 11:24
 */
public class MsgAgreementReceiver extends AbstractReceiver
{
    private Logger logger = LoggerFactory.getLogger(MsgAgreementReceiver.class);

    @Override
    public void receiveMessage(Object message) {
        logger.info("接收到push消息");
        MsgAgreement msgAgreement = JSON.parseObject(message.toString(),MsgAgreement.class);
        String toChannelId = msgAgreement.getToChannelId();
        Channel channel = CacheUtil.cacheChannel.get(toChannelId);
        if(null == channel){
            return;
        }
        channel.writeAndFlush(MsgUtil.obj2json(msgAgreement));
    }
}
