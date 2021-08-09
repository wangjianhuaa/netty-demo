package com.demo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/9 18:11
 */
@Component
public class NettyServer {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);

    /**
     * nio线程组
     */
    private final EventLoopGroup parentGroup  = new NioEventLoopGroup();
    private final EventLoopGroup childGroup  = new NioEventLoopGroup();

    private Channel channel;


    public ChannelFuture bing(InetSocketAddress address ){
        ChannelFuture channelFuture = null;

        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup,childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new MyChannelInitializer());

            channelFuture = b.bind(address).syncUninterruptibly();
            channel = channelFuture.channel();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        finally
        {
            if(null != channelFuture && channelFuture.isSuccess()){
                logger.info("netty-demo 2-05 websocket server start done.");
            }
            else {
                logger.error("netty-demo 2-05 websocket server start error.");
            }
        }
        return channelFuture;
    }
    public Channel getChannel(){
        return channel;
    }

    public void destroy(){
        if(null == channel){
            return;
        }
        channel.close();
        parentGroup.shutdownGracefully();
        childGroup.shutdownGracefully();
    }
}
