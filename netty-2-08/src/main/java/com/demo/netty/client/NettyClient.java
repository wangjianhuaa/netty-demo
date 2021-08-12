package com.demo.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/11 10:29
 */
public class NettyClient {

    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1",7397);
    }


    public void connect(String inetHost,int inetPort){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try
        {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .option(ChannelOption.AUTO_READ,true)
                    .channel(NioSocketChannel.class)
                    .handler(new MyChannelInitializer());
            ChannelFuture f = b.connect(inetHost, inetPort).sync();
            //添加监听，处理重连
            f.addListener(new MyChannelFutureListener());
            f.channel().closeFuture().sync();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            workerGroup.shutdownGracefully();
        }
    }
}
