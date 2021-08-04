package com.demo.netty;

import com.demo.netty.server.NettyServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

/**
 * @author wangjianhua
 * @Description 整合启动类
 * @date 2021/8/4 14:37
 */
@SpringBootApplication(scanBasePackages = "com.demo.netty")
public class NettyApplication implements CommandLineRunner {


    @Autowired
    private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class,args);
    }

    @Value("${netty.host}")
    private String host;

    @Value("${netty.port}")
    private int port;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("host = " + host);
        System.out.println("port = " + port);
        InetSocketAddress address = new InetSocketAddress(host,port);
        ChannelFuture channelFuture = nettyServer.bing(address);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
