package com.demo.netty.aio.channel;

import com.demo.netty.aio.server.AioServer;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/27/027 9:48
 */
public abstract class ChannelInitializer implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
    @Override
    public void completed(AsynchronousSocketChannel channel, AioServer attachment) {
        try {
            initChannel(channel);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //在此接受客户端连接
            attachment.serverSocketChannel().accept(attachment,this);
        }
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.getStackTrace();
    }

    /**
     * 通道初始化
     */
    protected abstract void initChannel(AsynchronousSocketChannel channel) throws Exception;
}
