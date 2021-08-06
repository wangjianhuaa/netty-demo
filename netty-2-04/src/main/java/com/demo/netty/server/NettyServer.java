package com.demo.netty.server;

import com.demo.netty.server.handler.MyChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 16:40
 */
public class NettyServer {

    /**
     * 配置服务端nio线程组
     */
    private EventLoopGroup parentGroup = new NioEventLoopGroup();
    private EventLoopGroup childGroup = new NioEventLoopGroup();

    private Channel channel;

    public Channel getChannel(){
        return channel;
    }

    public ChannelFuture bing(int port){
        ChannelFuture channelFuture = null;
        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup,childGroup)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer());
            channelFuture = b.bind(port).syncUninterruptibly();
            this.channel = channelFuture.channel();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(null != channelFuture && channelFuture.isSuccess()){
                System.out.println("netty demo server start done.");
            }
            else {
                System.out.println("netty demo server start error");
            }
        }
        return channelFuture;
    }

    public void destroy(){
        if(null == channel){
            return;
        }
        channel.close();
        childGroup.shutdownGracefully();
        parentGroup.shutdownGracefully();
    }
}
