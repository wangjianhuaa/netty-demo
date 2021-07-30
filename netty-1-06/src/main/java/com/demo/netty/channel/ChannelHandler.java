package com.demo.netty.channel;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/30/030 19:59
 **/
public class ChannelHandler {
    /**
     * 用于存放用户Channel信息 也可以建立map结构模拟不同的消息群
     */
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
