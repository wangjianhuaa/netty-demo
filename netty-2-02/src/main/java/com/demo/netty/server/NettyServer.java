package com.demo.netty.server;

import com.demo.netty.server.handler.MyChannelInitializer;
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
 * @date 2021/8/5 18:20
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
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new MyChannelInitializer());
            ChannelFuture f = b.bind(port);
            System.out.println("netty demo 2-02 protobuf start done.");
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
