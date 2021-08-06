package com.demo.netty.client;

import com.demo.netty.client.handler.MyChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 15:24
 */
public class NettyClient {

    /**
     * 配置nio线程组
     */
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    private Channel channel;

    public ChannelFuture connect(String inetHost,int inetPort){
        ChannelFuture channelFuture = null;
        try
        {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ,true);
            b.handler(new MyChannelInitializer());
            channelFuture = b.connect(inetHost, inetPort).syncUninterruptibly();
            this.channel = channelFuture.channel();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(null != channelFuture && channelFuture.isSuccess()){
                System.out.println("netty demo 2-04 start done.");
            }
            else {
                System.err.println("netty demo 2-04 start error");
            }
        }
        return channelFuture;
    }

    public void destroy(){
        if(null == channel){
            return;
        }
        channel.close();
        workerGroup.shutdownGracefully();
    }
}
