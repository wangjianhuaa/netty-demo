package com.demo.netty.server;

import com.demo.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/30/030 20:01
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 链接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当有客户端链接后,添加到channelGroup通信组
        ChannelHandler.channelGroup.add(ctx.channel());
        //日志信息
        SocketChannel channel = (SocketChannel)ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息:有客户端链接到本服务端");
        System.out.println("链接报告ip:==>"+channel.localAddress().getHostString());
        System.out.println("链接报告port:==>"+channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        //通知客户端链接建立成功
        String str = "客户端:你已成功连入服务端"+""+new Date()+""+channel.localAddress().getHostString()+"\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接"+ctx.channel().localAddress().toString());
        //客户端退出后 从channelGroup中移除
        ChannelHandler.channelGroup.remove(ctx.channel());
    }

    /**
     * 读取
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接受传来的消息 解码在initial中实现
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+
                "接收到消息"+msg);
        //收到消息后 群发给客户端
        String str = "服务端收到:"+new Date()+""+msg+"\r\n";
        ChannelHandler.channelGroup.writeAndFlush(str);
    }

    /**
     * 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息:\r\n"+cause.getMessage());
    }
}
