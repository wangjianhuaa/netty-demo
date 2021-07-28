package com.demo.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/28/028 11:25
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel socketChannel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息:客户端连接到服务端");
        System.out.println("链接报告ip:==>"+socketChannel.localAddress().getHostString());
        System.out.println("链接报告port:==>"+socketChannel.localAddress().getPort());
        System.out.println("链接报告完毕");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //这儿不写解码了 解码在管道里面实现
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"接收到消息:"+msg);
    }
}
