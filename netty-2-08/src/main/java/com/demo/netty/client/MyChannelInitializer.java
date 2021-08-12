package com.demo.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/11 9:38
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //基于换行符的解码器
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //String解码器
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //String编码器
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        channel.pipeline().addLast(new MyClientHandler());
    }
}
