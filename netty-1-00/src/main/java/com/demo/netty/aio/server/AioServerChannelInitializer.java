package com.demo.netty.aio.server;

import com.demo.netty.aio.channel.ChannelInitializer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/27/027 9:54
 */
public class AioServerChannelInitializer extends ChannelInitializer {


    @Override
    protected void initChannel(AsynchronousSocketChannel channel) throws Exception {
        channel.read(ByteBuffer.allocate(1024),10, TimeUnit.SECONDS,null,new AioServerHandler(channel, Charset.forName("GBK")));
    }
}
