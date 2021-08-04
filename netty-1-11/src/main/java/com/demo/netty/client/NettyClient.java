package com.demo.netty.client;

import com.demo.netty.client.handler.MyChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/4 10:50
 */
public class NettyClient {


    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1",7397,7398);
    }



    private void connect(String inetHost,int inetPort,int bindPort){
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try
        {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioDatagramChannel.class)
                    .handler(new MyChannelInitializer());
            Channel ch = b.bind(bindPort).sync().channel();
            //向目标端口发送消息
            ch.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("客户端端口7397 hello", Charset.forName("GBK"))
            ,new InetSocketAddress(inetHost,inetPort))).sync();
            ch.closeFuture().await();
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        finally
        {
            workerGroup.shutdownGracefully();
        }
    }
}
