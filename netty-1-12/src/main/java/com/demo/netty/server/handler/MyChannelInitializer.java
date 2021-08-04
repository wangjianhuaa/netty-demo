package com.demo.netty.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/4 13:38
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //数据编码操作
        channel.pipeline().addLast(new HttpResponseEncoder());
        //数据解码操作
        channel.pipeline().addLast(new HttpRequestDecoder());
        //自己的数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }
}
