package com.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/12 18:02
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        /**
         * 心跳检测
         * readerIdleTimeSeconds    读超时时间
         * writerIdleTimeSeconds    写超时时间
         * allIdleTimeSeconds       读写超时时间
         * 可以指定 TimeUnit
         */
        channel.pipeline().addLast(new IdleStateHandler(2,2,2));
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        channel.pipeline().addLast(new MyServerHandler());
    }
}
