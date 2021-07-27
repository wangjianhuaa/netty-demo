package com.demo.netty.nio.server;

import com.demo.netty.nio.channel.ChannelAdapter;
import com.demo.netty.nio.channel.ChannelHandler;

import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/27/027 21:10
 **/
public class NioServerHandler extends ChannelAdapter {

    public NioServerHandler(Selector selector, Charset charset){
        super(selector,charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try
        {
            System.out.println("nio链接报告LocalAddress:"+ctx.channel().getLocalAddress());
            ctx.writeAndFlush("hi nioServer to msg for u!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"接收到的消息:"+msg);
        ctx.writeAndFlush("hi 接收到消息成功! \r\n");
    }
}
