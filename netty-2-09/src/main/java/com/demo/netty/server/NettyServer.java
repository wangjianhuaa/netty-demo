package com.demo.netty.server;

import com.demo.netty.service.ExtServerService;
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
 * @date 2021/8/13 11:35
 */
public class NettyServer implements Callable<Channel> {

    private Logger logger  = LoggerFactory.getLogger(NettyServer.class);

    private InetSocketAddress address;

    private ExtServerService extServerService;

    public NettyServer(InetSocketAddress address, ExtServerService extServerService) {
        this.address = address;
        this.extServerService = extServerService;
    }

    /**
     * 配置服务端nio线程组
     */
    private final EventLoopGroup parentGroup = new NioEventLoopGroup();
    private final EventLoopGroup childGroup  = new NioEventLoopGroup();

    private Channel channel;

    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup,childGroup)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer(extServerService));
            channelFuture = b.bind(address).syncUninterruptibly();
            this.channel = channelFuture.channel();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        finally
        {
            if(null != channelFuture &&  channelFuture.isSuccess()){
                logger.info("netty demo server start done.");
            }
            else {
                logger.error("netty demo server start error.");
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

    public Channel getChannel() {
        return channel;
    }
}
