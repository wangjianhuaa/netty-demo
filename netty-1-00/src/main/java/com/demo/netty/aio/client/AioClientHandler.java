package com.demo.netty.aio.client;

import com.demo.netty.aio.channel.ChannelAdapter;
import com.demo.netty.aio.channel.ChannelHandler;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description 客户端消息处理器
 * @date 2021/7/27/027 16:00
 */
public class AioClientHandler extends ChannelAdapter {

    public AioClientHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try{
            System.out.println("netty demo |链接报告信息:"+ctx.channel().getRemoteAddress());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void channelInActive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("netty demo|服务端收到:"+new Date()+""+msg+"\r\n");
        ctx.writeAndFlush("客户端消息处理成功!"+"\r\n");
    }
}
