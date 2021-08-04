package com.demo.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/4 14:42
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MyServerHandler.class);

    /**
     * 保持连接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        logger.info("链接报告开始");
        logger.info("链接报告信息:有一客户端链接到本服务端");
        logger.info("链接报告ip:==>"+channel.localAddress().getHostString());
        logger.info("连接报告port:==>"+channel.localAddress().getPort());
        logger.info("链接报告结束");
        //通知客户端链接建立成功
        String str =  "通知客户端:连接建立成功"+""+new Date()+""
                +channel.localAddress().getHostString()+"\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 连接断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端断开链接{}",ctx.channel().localAddress().toString());
    }

    /**
     * 信息读取
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接受msg消息
        logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"服务端接收到消息"+msg);
        //通知客户端链接消息发送成功
        String str = "服务端收到:"+new Date()+""+msg+"\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 异常抓取
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.error("异常信息:\r\n"+cause.getMessage());
    }
}
