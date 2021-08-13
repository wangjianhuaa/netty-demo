package com.demo.netty.redis;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 11:25
 */
public  abstract class AbstractReceiver {
    /**
     * 接收消息
     * @param message 消息
     */
    public abstract void receiveMessage(Object message);
}
