package com.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/30/030 20:29
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //基于换行符
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //String解码器 GBK
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //String编码器 GBK
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        //自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());

    }
}
