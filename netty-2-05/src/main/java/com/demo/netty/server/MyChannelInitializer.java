package com.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/9 18:08
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast("http-codec",new HttpServerCodec());
        channel.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));
        channel.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
        //自己的数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }
}
