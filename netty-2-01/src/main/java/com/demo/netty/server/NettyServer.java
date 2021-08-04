package com.demo.netty.server;

import com.demo.netty.server.handler.MyChannelInitializer;
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
 * @date 2021/8/4 14:42
 */
@Component
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    /**
     * nio线程组配置
     */

    private final EventLoopGroup parentGroup = new NioEventLoopGroup();
    private final EventLoopGroup childGroup = new NioEventLoopGroup();

    /**
     * 管道
     */
    private Channel channel;

    public ChannelFuture bing(InetSocketAddress address){

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
            if(null!=channelFuture && channelFuture.isSuccess())
            {
                logger.info("springboot 整合netty start done.");
            }
            else
            {
                logger.error("springboot 整合netty start error.");
            }
        }
        return channelFuture;
    }

    /**
     * 关闭方法
     */
    public void destroy(){
        if(null == channel)
        {
            return;
        }
        parentGroup.shutdownGracefully();
        childGroup.shutdownGracefully();
    }

    public Channel getChannel() {
        return channel;
    }
}
