package com.demo.netty.client.handler;

import com.alibaba.fastjson.JSON;
import com.demo.netty.util.MsgUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 11:06
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 链接活跃
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息:本客户端连接到服务端:channelId==>"+channel.id());
        System.out.println("链接报告ip:"+channel.localAddress().getHostString());
        System.out.println("链接报告port:"+channel.localAddress().getPort());
        System.out.println("链接报告结束");
        //通知服务端链接建立成功
        String str = "通知 服务端 客户端与你成功建立联系"+""+new Date()+""+channel.localAddress().getHostString();
        ctx.writeAndFlush(MsgUtil.buildMsg(channel.id().toString(),str));
    }

    /**
     * 连接断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开链接"+ctx.channel().localAddress().toString());
    }

    /**
     * 接收来自服务端的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"接收到消息类型"+msg.getClass());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"接收到消息内容"+ JSON.toJSONString(msg));
    }

    /**
     * 异常捕获
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println(cause.getMessage());
    }
}
