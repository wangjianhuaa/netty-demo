package com.demo.netty.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/11 10:33
 */
public class MyChannelFutureListener implements ChannelFutureListener {

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if(channelFuture.isSuccess()){
            System.out.println("netty demo client 2-08 start done.(listener)");
            return;
        }
        final EventLoop loop = channelFuture.channel().eventLoop();
        loop.schedule(() -> {
            try{
                new NettyClient().connect("127.0.0.1",7397);
                System.out.println("netty demo client start done (listener)");
                Thread.sleep(500);
            }
            catch (Exception e)
            {
                System.out.println("netty demo client go reconnect...waiting");
            }
        },1L, TimeUnit.SECONDS);
    }
}
