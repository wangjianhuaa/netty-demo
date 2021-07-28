package com.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wangjianhua
 * @Description 套接字初始化
 * @date 2021/7/28/028 9:48
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("链接报告开始");
        System.out.println("链接报告信息:客户端连接到本服务端");
        System.out.println("链接报告信息:ip==>"+socketChannel.localAddress().getHostString());
        System.out.println("链接报告信息:port==>"+socketChannel.localAddress().getPort());
        System.out.println("链接报告完毕");
    }
}
