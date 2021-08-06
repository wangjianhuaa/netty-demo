package com.demo.netty.client.handler;

import com.demo.netty.codec.ObjDecoder;
import com.demo.netty.codec.ObjEncoder;
import com.demo.netty.domain.FileTransferProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 16:19
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(FileTransferProtocol.class));
        channel.pipeline().addLast(new ObjEncoder(FileTransferProtocol.class));
        //添加自己的接收数据实现方法
        channel.pipeline().addLast(new MyClientHandler());
    }
}
