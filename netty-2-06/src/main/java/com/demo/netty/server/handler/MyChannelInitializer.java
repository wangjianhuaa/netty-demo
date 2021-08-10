package com.demo.netty.server.handler;

import com.demo.netty.codec.ObjDecoder;
import com.demo.netty.codec.ObjEncoder;
import com.demo.netty.domain.TransportProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 11:41
 */
@Component
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Autowired
    private MyServerHandler myServerHandler;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new ObjDecoder(TransportProtocol.class));
        channel.pipeline().addLast(new ObjEncoder(TransportProtocol.class));
        channel.pipeline().addLast(myServerHandler);
    }
}
