package com.demo.ark.server.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 16:35
 */
public class WsNettyServer implements Callable<Channel> {

    private Logger logger = LoggerFactory.getLogger(WsNettyServer.class);

    private InetSocketAddress address;

    public WsNettyServer(InetSocketAddress address) {
        this.address = address;
    }

    /**
     * 配置服务端nio线程组
     */
    private EventLoopGroup parentGroup = new NioEventLoopGroup(2);
    private EventLoopGroup childGroup = new NioEventLoopGroup();

    private Channel channel;

    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup,childGroup)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childHandler(new MyWsChannelInitializer())
                    .channel(NioServerSocketChannel.class);

            channelFuture = b.bind(address).syncUninterruptibly();
            this.channel = channelFuture.channel();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        finally
        {
            if (null != channelFuture && channelFuture.isSuccess()) {
                logger.info("netty demo server-websocket start done.");
            }
            else
            {
                logger.info("netty demo server-websocket start error.");
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
