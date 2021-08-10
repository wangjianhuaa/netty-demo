package com.demo.netty.client;

import com.demo.netty.codec.RpcDecoder;
import com.demo.netty.codec.RpcEncoder;
import com.demo.netty.msg.Request;
import com.demo.netty.msg.Response;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 18:21
 */
public class ClientSocket implements Runnable{

    private ChannelFuture future;
    @Override
    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try
        {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ,true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new RpcDecoder(Response.class));
                    channel.pipeline().addLast(new RpcEncoder(Request.class));
                    channel.pipeline().addLast(new MyClientHandler());
                }
            });
            ChannelFuture f = b.connect("127.0.0.1", 7397).sync();
            this.future = f;
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


    public ChannelFuture getFuture() {
        return future;
    }

    public void setFuture(ChannelFuture future) {
        this.future = future;
    }
}
