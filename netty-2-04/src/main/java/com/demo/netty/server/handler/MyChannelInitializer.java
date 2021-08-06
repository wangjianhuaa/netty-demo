package com.demo.netty.server.handler;

import com.demo.netty.codec.ObjDecoder;
import com.demo.netty.codec.ObjEncoder;
import com.demo.netty.domain.FileTransferProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 17:17
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new ObjDecoder(FileTransferProtocol.class));
        channel.pipeline().addLast(new ObjEncoder(FileTransferProtocol.class));
        channel.pipeline().addLast(new MyServerHandler());
    }
}
