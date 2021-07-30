package com.demo.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/30/030 20:44
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        System.out.println("链接报告开始");
        System.out.println("链接报告信息:本客户端连接到服务器:channelId:"+channel.id());
        System.out.println("链接报告完毕");
    }
}
