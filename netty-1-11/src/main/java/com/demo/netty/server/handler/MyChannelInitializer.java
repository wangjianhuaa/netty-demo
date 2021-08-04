package com.demo.netty.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/4 11:07
 */
public class MyChannelInitializer extends ChannelInitializer<NioDatagramChannel> {


    private EventLoopGroup group= new NioEventLoopGroup();

    @Override
    protected void initChannel(NioDatagramChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //解码转String
        pipeline.addLast("stringDecoder",new StringDecoder(Charset.forName("GBK")));
        pipeline.addLast(new MyServerHandler());
    }
}
