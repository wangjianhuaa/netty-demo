package com.demo.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/28/028 15:03
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端与服务端主动建立连接时，通道处于活跃状态 该通信通道活跃并可以处理数据
     * @param ctx 通信处理器作用域
     * @throws Exception 异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息:客户端连接到服务端");
        System.out.println("链接报告ip:==>"+channel.localAddress().getHostString());
        System.out.println("链接报告端口:==>"+channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        //加入对客户端的通知 别忘记换行符
        String str = "通知客户端链接建立成功"+""+new Date()+""+channel.localAddress().getHostString()+"\r\n";
        ByteBuf buf  = Unpooled.buffer(str.getBytes().length);
        //写入时进行编码
        buf.writeBytes(str.getBytes("GBK"));
        ctx.writeAndFlush(buf);
    }

    /**
     * 当客户端断开与服务端的连接时，通道处于不活跃状态 通信通道关闭且不可以传输数据
     * @param ctx 通信处理器作用域
     * @throws Exception 异常
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接:"+ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息 这里解码在MyChannelInitializer中实现
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        .format(new Date())+"接收到消息"+msg);
        //通知客户端链接发送成功
        String str = "服务端收到:"+new Date()+""+msg+"\r\n";
        ByteBuf buf = Unpooled.buffer(str.getBytes().length);
        buf.writeBytes(str.getBytes("GBK"));
        ctx.writeAndFlush(buf);
    }

    /**
     * 发生异常时的捕获
     * @param ctx 通信处理器作用域
     * @param cause 被异常抛出的类
     * @throws Exception 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息:\r\n"+cause.getMessage());
    }
}
