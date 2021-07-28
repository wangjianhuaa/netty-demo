package com.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/28/028 10:37
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        System.out.println("链接报告开始:");
        System.out.println("链接报告信息:客户端连接到本服务端");
        System.out.println("链接报告ip:==>"+channel.localAddress().getHostString());
        System.out.println("链接报告port:==>"+channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        //在管道中添加自己的接受数据的方法
        channel.pipeline().addLast(new MyServerHandler());
    }
}
