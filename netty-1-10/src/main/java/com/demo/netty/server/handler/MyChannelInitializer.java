package com.demo.netty.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/3/003 20:29
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //基于换行符
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //String 编码器
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        //String 解码器
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //自己实现的处理器
        channel.pipeline().addLast(new MyServerHandler());
    }
}
