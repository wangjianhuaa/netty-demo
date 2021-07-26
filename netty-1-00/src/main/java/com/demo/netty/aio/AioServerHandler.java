package com.demo.netty.aio;


import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/26/026 19:59
 **/
public class AioServerHandler extends ChannelAdapter {

    public AioServerHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {

    }

    @Override
    public void channelInActive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {

    }
}
