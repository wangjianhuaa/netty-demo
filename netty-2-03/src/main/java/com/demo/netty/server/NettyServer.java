package com.demo.netty.server;

import com.demo.netty.server.handler.MyChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 11:31
 */
public class NettyServer {

    public static void main(String[] args) {
        new NettyServer().bing(7397);
    }




    private void bing(int port){
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup,childGroup)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer());
            ChannelFuture f = b.bind(port).sync();
            System.out.println("netty demo 2-03 protostuff传输处理数据 server start done.");
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
