package com.demo.netty.server.handler;

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
 * @date 2021/8/6 11:32
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端与本服务端链接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        System.out.println("连接报告开始");
        System.out.println("有一客户端连接到本服务端:channelId:"+channel.id());
        System.out.println("链接ip:"+channel.localAddress().getHostString());
        System.out.println("链接port:"+channel.localAddress().getPort());
        System.out.println("链接报告结束");
        //通知客户端连接建立成功
        String str = "客户端 本服务端已与你成功建立连接"+""+new Date() +
                channel.localAddress().getHostString()+"\r\n";
        ctx.writeAndFlush(MsgUtil.buildMsg(channel.id().toString(),str));
    }

    /**
     * 断开链接
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端断开链接"+ctx.channel().localAddress().toString());
    }

    /**
     * 读取客户端发送的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date())+"接收到消息类型"+msg.getClass());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd")
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
