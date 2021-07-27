package com.demo.netty.aio.server;


import com.demo.netty.aio.channel.ChannelAdapter;
import com.demo.netty.aio.channel.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

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
        try {
            System.out.println("aio服务处理器==>AioServerHandler|链接报告信息"+ctx.channel().getRemoteAddress());
            //通知客户端链接建立成功
            ctx.writeAndFlush("通知服务端链接建立成功"+""+ new Date()+ "" +ctx.channel().getRemoteAddress()+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInActive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("aio服务处理器==>AioServerHandler|服务端收到:"+new Date() + msg+"\r\n");
        ctx.writeAndFlush("服务端信息处理成功!\r\n");
    }
}
