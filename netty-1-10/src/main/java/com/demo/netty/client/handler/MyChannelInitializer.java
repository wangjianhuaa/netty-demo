package com.demo.netty.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/3/003 20:04
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //基于换行符
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //解码转string
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //String 编码器
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        //添加自己的数据实现方法
        //消息入站处理器
        channel.pipeline().addLast(new MyInMsgHandler());
        //消息出站处理器
        channel.pipeline().addLast(new MyOutMsgHandler());
    }
}
