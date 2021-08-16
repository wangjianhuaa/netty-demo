package com.demo.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.SSLException;
import java.io.File;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/16 14:39
 */
public class NettyClient {

    public static void main(String[] args) throws SSLException {
        new NettyClient().connect("127.0.0.1",7397);
    }

    private void connect(String inetHost, int inetPort) throws SSLException {

        //引入SSL安全验证
        File certChainFile = new File("E:\\workdemo\\netty\\netty-2-13\\src\\main\\java\\com\\demo\\netty\\ssl\\client\\client.crt");
        File keyFile = new File("E:\\workdemo\\netty\\netty-2-13\\src\\main\\java\\com\\demo\\netty\\ssl\\client\\pkcs8_client.key");
        File rootFile = new File("E:\\workdemo\\netty\\netty-2-13\\src\\main\\java\\com\\demo\\netty\\ssl\\client\\ca.crt");
        SslContext sslCtx = SslContextBuilder.forClient().keyManager(certChainFile, keyFile).trustManager(rootFile).build();

        //配置客户端NIO线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new MyChannelInitializer(sslCtx));
            ChannelFuture f = b.connect(inetHost, inetPort).sync();
            System.out.println("netty demo 2-13 client start done. ");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
