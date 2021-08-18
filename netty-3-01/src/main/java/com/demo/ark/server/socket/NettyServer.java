package com.demo.ark.server.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 15:33
 */
public class NettyServer implements Callable<Channel> {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private InetSocketAddress address;

    public NettyServer(InetSocketAddress address) {
        this.address = address;
    }

    /**
     * 服务端NIO线程组
     */
    private EventLoopGroup parentGroup = new NioEventLoopGroup();
    private EventLoopGroup childGroup = new NioEventLoopGroup();

    private Channel channel;

    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup,childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new MyChannelInitializer());

            channelFuture = b.bind(address).syncUninterruptibly();
            this.channel = channelFuture.channel();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        finally
        {
            if(null!= channelFuture && channelFuture.isSuccess()){
                logger.info("netty demo server-socket start done.");
            }
            else {
                logger.error("netty demo server-socket start error.");
            }
        }
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

    public Channel getChannel(){
        return channel;
    }
}
