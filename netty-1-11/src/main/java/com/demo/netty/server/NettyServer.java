package com.demo.netty.server;

import com.demo.netty.server.handler.MyChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/4 11:00
 */
public class NettyServer {


    public static void main(String[] args) {
        new NettyServer().bing(7397);
    }


    private void bing(int port){
        EventLoopGroup group = new NioEventLoopGroup();
        try
        {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    //广播
                    .option(ChannelOption.SO_BROADCAST,true)
                    //设置UDP读缓冲区为2M
                    .option(ChannelOption.SO_RCVBUF,2048*1024)
                    //设置UDP写缓冲区为1M
                    .option(ChannelOption.SO_SNDBUF,1024*1024)
                    .handler(new MyChannelInitializer());

            ChannelFuture f = b.bind(port).sync();
            System.out.println("netty demo 1-11 udp start done.");
            f.channel().closeFuture().sync();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            group.shutdownGracefully();
        }
    }
}
