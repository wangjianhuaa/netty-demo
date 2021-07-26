package com.demo.netty.aio;


import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description 模仿netty的Channel适配器
 * @date 2021/7/26/026 19:53
 **/
public abstract class ChannelAdapter implements CompletionHandler<Integer,Object> {

    private AsynchronousSocketChannel channel;

    private Charset charset;


    public ChannelAdapter(AsynchronousSocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
        if(channel.isOpen()){
            channelActive(new ChannelHandler(channel,charset));
        }
    }

    @Override
    public void completed(Integer result, Object attachment) {

    }

    @Override
    public void failed(Throwable exc, Object attachment) {

    }

    public abstract void channelActive(ChannelHandler ctx);

    public abstract void channelInActive(ChannelHandler ctx);

    /**
     * 读取消息抽象类
     */
    public abstract void channelRead(ChannelHandler ctx,Object msg);
}
