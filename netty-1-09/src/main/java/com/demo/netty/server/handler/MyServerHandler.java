package com.demo.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/31/031 20:09
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 链接活跃 可传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息:有一客户端链接到本服务端");
        System.out.println("链接报告ip:==>"+channel.localAddress().getHostString());
        System.out.println("链接报告port:==>"+channel.localAddress().getPort());
        System.out.println("链接报告结束");
    }

    /**
     * 读取数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接受msg消息
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"接收到消息"+msg);
        ctx.writeAndFlush("hello ok");
    }
}
