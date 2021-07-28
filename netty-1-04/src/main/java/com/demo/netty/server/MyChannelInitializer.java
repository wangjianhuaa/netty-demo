package com.demo.netty.server;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;


/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/28/028 15:29
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //基于换行符的解码器
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //解码转String 用GBK就填写GBK 用UTF-8就填写UTF-8
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //添加自己定义的处理器中接收数据的方法
        channel.pipeline().addLast(new MyServerHandler());
    }
}
