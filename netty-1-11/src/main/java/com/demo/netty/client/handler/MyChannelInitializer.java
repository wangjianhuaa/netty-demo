package com.demo.netty.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/4 10:41
 */
public class MyChannelInitializer extends ChannelInitializer<NioDatagramChannel> {


    @Override
    protected void initChannel(NioDatagramChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //String 解码
        pipeline.addLast("stringDecoder",new StringDecoder(Charset.forName("GBK")));
        pipeline.addLast(new MyClientHandler());
    }
}
