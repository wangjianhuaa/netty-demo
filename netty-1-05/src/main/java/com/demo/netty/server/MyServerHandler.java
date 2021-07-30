package com.demo.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/30/030 18:11
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 客户端链接服务端时  此时通道为活跃状态 客户端与服务端建立了通信通道且可以传输数据
     * @param ctx 通信处理器作用域
     * @throws Exception 异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("客户端连接到服务端");
        System.out.println("链接报告ip：==>"+channel.localAddress().getHostString());
        System.out.println("链接报告port:==>"+channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        //通知客户端 链接建立成功
        String str = "通知客户端链接建立成功:"+""+new Date()+""+channel.localAddress().getHostString()+"\r\n";
        ctx.writeAndFlush(str);

    }

    /**
     * 断开连接
     * @param ctx 通信处理器作用域
     * @throws Exception 异常
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开链接:"+ctx.channel().localAddress().toString());
    }

    /**
     * 读取信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //这里不需要自己解码 解码写在ChannelInitial里面
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+""+"接收到消息"+msg);
        //通知客户端链消息发送成功
        String str = ""+new Date()+""+msg+"\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 异常抓取 可以打印日志并记录异常和关闭链接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("\r\n"+cause.getMessage());
    }
}
