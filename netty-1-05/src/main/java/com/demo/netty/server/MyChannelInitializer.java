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
 * @date 2021/7/30/030 18:23
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //基于换行符号 最大长度1024
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //string 解码器 GBk
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //String 编码器 GBK
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        //添加自己的handler
        channel.pipeline().addLast(new MyServerHandler());
    }
}
