package com.demo.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/31/031 19:04
 **/
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 连接 活跃状态 可传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        System.out.println("连接报告开始");
        System.out.println("链接报告:本客户端连接到服务端:channelId:"+channel.id());
        System.out.println("链接报告ip:"+channel.localAddress().getHostString());
        System.out.println("链接报告port:"+channel.localAddress().getPort());
        System.out.println("连接报告结束");
        //通知客户端链接建立成功
        String str = "通知服务端链接建立成功"+""+new Date()+channel.localAddress().getHostString()+"\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 断开连接
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("链接断开"+ctx.channel().localAddress().toString());
    }

    /**
     * 信息读取
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接受msg信息 解码放在initial里面
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"接收到消息"+msg);
        //通知客户端链连接建立成功
        String str = "客户端收到:"+new Date()+""+msg+"\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 异常抓取
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息:"+"\r\n"+cause.getMessage());
    }
}
