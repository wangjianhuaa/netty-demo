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
 * @date 2021/8/5 14:04
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
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.AUTO_READ,true)
                    .handler(new MyChannelInitializer());
            ChannelFuture f = b.bind(inetHost, inetPort).sync();
            System.out.println("netty demo 2-02 protobuf处理数据 start done.");
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),"" +
                    "你好，使用protobuf通信格式的服务端，我是测试用例"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),"" +
                    "你好，使用protobuf通信格式的服务端，我是测试用例"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),"" +
                    "你好，使用protobuf通信格式的服务端，我是测试用例"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),"" +
                    "你好，使用protobuf通信格式的服务端，我是测试用例"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),"" +
                    "你好，使用protobuf通信格式的服务端，我是测试用例"));
            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),"" +
                    "你好，使用protobuf通信格式的服务端，我是测试用例"));
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
