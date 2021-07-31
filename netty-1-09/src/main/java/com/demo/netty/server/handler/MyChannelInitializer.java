package com.demo.netty.server.handler;

import com.demo.netty.util.MyDecoder;
import com.demo.netty.util.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/31/031 20:20
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //自定义解码器
        channel.pipeline().addLast(new MyDecoder());
        //自定义编码器
        channel.pipeline().addLast(new MyEncoder());
        //添加自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }
}
