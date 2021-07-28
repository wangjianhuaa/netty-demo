package com.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description 这里处理时要注意 写时候的逻辑使用换行符解决半包粘包问题 所以在结尾使用换行符
 * 表示这是一整块内容
 * @date 2021/7/28/028 13:37
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //加入解码器
        //基于换行符号的解码器
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //基于指定字符串【换行符 这样功能等同于LineBasedFrameDecoder】
        //channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, false, Delimiters.lineDelimiter()));
        //基于最大长度
        //channel.pipeline().addLast(new FixedLengthFrameDecoder(4));
        //解码转String GBK或者UTF-8
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        //在管道中添加自己实现的接收数据方法
        channel.pipeline().addLast(new MyServerHandler());
    }
}
