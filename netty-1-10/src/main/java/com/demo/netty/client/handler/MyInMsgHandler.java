package com.demo.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description 读取消息处理器
 * @date 2021/8/2/002 19:58
 **/
public class MyInMsgHandler extends ChannelInboundHandlerAdapter{

    /**
     * 链接活跃通道
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息:本客户端连接到服务端.channelId:"+channel.id());
        System.out.println("链接报告ip:==>"+channel.localAddress().getHostString());
        System.out.println("连接报告port:==>"+channel.localAddress().getPort());
        System.out.println("链接报告结束.");
        //通知客户端链接建立成功
        String str = "通知服务端连接建立成功"+""+new Date()+""+channel.localAddress().getHostString()+"\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开链接"+ctx.channel().localAddress().toString());
    }

    /**
     * 消息读取
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息 消息转码另外实现
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"客户端收到消息"+msg);
        //通知客户端消息发送成功
        String str = "随机数"+Math.random()*10+"\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 异常抓取
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常消息:'\r\n"+cause.getMessage());
    }
}
