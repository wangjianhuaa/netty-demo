package com.demo.netty.client.handler;

import com.demo.netty.domain.MsgBody;
import com.googlecode.protobuf.format.JsonFormat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/5 14:05
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        System.out.println("连接报告开始");
        System.out.println("连接报告信息:本客户端连接到服务端 channelId:"+channel.id());
        System.out.println("链接报告ip:"+channel.localAddress().getHostString());
        System.out.println("连接报告port:"+channel.localAddress().getPort());
        System.out.println("连接报告完毕");
        //通知客户端连接建立成功
        String str = "通知 服务端连接建立成功"+""+new Date()+""+channel.localAddress().getHostString();
        ctx.writeAndFlush(str);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress().toString()+"断开链接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收msg消息
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"接收到消息类型"+msg.getClass());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"接收到消息内容"+ JsonFormat.printToString((MsgBody)msg));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println(cause.getMessage());
    }
}
