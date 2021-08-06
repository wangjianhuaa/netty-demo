package com.demo.netty.client;

import com.demo.netty.client.handler.MyChannelInitializer;
import com.demo.netty.util.MsgUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 11:05
 */
public class NettyClient {


    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1",7397);
    }



    private void connect(String inetHost,int inetPort){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try
        {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .option(ChannelOption.AUTO_READ,true)
                    .handler(new MyChannelInitializer())
                    .channel(NioSocketChannel.class);
            ChannelFuture f = b.connect(inetHost, inetPort).sync();
            System.out.println("netty demo 2-03 protostuff 案例 start done.");
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),
                    "你好，使用protobuf通信格式的服务端，我是服务端"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),
                    "你好，使用protobuf通信格式的服务端，我是服务端"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),
                    "你好，使用protobuf通信格式的服务端，我是服务端"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),
                    "你好，使用protobuf通信格式的服务端，我是服务端"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),
                    "你好，使用protobuf通信格式的服务端，我是服务端"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),
                    "你好，使用protobuf通信格式的服务端，我是服务端"));
            f.channel().closeFuture().sync();
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
