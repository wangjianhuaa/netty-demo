package com.demo.netty.server.handler;

import com.demo.netty.codec.ObjDecoder;
import com.demo.netty.codec.ObjEncoder;
import com.demo.netty.domain.MsgInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 11:40
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new ObjDecoder(MsgInfo.class));
        channel.pipeline().addLast(new ObjEncoder(MsgInfo.class));
        channel.pipeline().addLast(new MyServerHandler());
    }
}
