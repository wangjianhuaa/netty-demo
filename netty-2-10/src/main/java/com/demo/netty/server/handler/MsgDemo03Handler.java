package com.demo.netty.server.handler;

import com.demo.netty.domain.MsgDemo03;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 18:20
 */
public class MsgDemo03Handler extends SimpleChannelInboundHandler<MsgDemo03> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgDemo03 msg) throws Exception {
        System.out.println("\r\n msg handler ing......  ");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收消息的处理器：" + this.getClass().getName());
        System.out.println("channelId：" + msg.getChannelId());
        System.out.println("消息内容：" + msg.getDemo03());
    }
}
