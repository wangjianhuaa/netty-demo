package com.demo.netty.nio.client;

import com.demo.netty.nio.channel.ChannelAdapter;
import com.demo.netty.nio.channel.ChannelHandler;

import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description nio 客户端处理器
 * @date 2021/7/27/027 21:19
 **/
public class NioClientHandler extends ChannelAdapter {

    public NioClientHandler(Selector selector, Charset charset) {
        super(selector, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try
        {
            System.out.println("链接报告LocalAddress:"+ctx.channel().getLocalAddress());
            ctx.writeAndFlush("hi nioClient to msg for u \r\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"接收到消息"+msg);
        ctx.writeAndFlush("hi 接收到nioClient消息 \r\n");
    }
}
