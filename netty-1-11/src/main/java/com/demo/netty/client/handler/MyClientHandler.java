package com.demo.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description UDP客户端处理器
 * @date 2021/8/4 10:45
 */
public class MyClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    /**
     * 接受服务端发送的内容
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String msg = packet.content().toString(Charset.forName("GBK"));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date())+"UDP客户端接收到信息:"+msg);
    }
}
