package com.demo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/28/028 13:47
 */
public class NettyServer {
    public static void main(String[] args) {
        new NettyServer().bing(7397);
    }

    private void bing(int port){
        //配置服务端nio线程组
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        //NioEventLoopGroup extends MultithreadEventLoopGroup
        // Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup,childGroup)
                    //非阻塞模式
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new MyChannelInitializer());
            ChannelFuture f = b.bind(port).sync();
            System.out.println("管道配置解码器 netty demo start done.");
            f.channel().closeFuture().sync();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }
    }
}
