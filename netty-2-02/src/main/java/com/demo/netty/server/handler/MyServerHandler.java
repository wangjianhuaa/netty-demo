package com.demo.netty.server.handler;

import com.demo.netty.domain.MsgBody;
import com.demo.netty.util.MsgUtil;
import com.googlecode.protobuf.format.JsonFormat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/5 18:10
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 活跃链接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        System.out.println("连接报告开始");
        System.out.println("链接报告信息:有一客户端连接到本服务端");
        System.out.println("链接报告ip:"+channel.localAddress().getHostString());
        System.out.println("链接报告port:"+channel.localAddress().getPort());
        System.out.println("链接报告结束");
        String str =  "通知客户端链接建立成功"+new Date()+""+channel.localAddress().getHostString()+"\r\n";
        ctx.writeAndFlush(MsgUtil.buildMsg(channel.id().toString(),str));
    }

    /**
     * 连接断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端链接断开:"+ctx.channel().localAddress().toString());
    }

    /**
     * 读取消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接受msg消息
        System.out.println(new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date())+"接收到消息类型"+msg.getClass());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date())+"接收到消息内容"+ JsonFormat.printToString((MsgBody)msg));
    }

    /**
     * 异常捕获
     */
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        ctx.close();
//        System.out.println(cause.getMessage());
//    }
}
