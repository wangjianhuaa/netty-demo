package com.demo.netty;

import com.demo.netty.server.NettyServer;
import io.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 13:49
 */
@SpringBootApplication(scanBasePackages = "com.demo.netty")
public class NettyApplication6 implements CommandLineRunner
{
    private Logger logger = LoggerFactory.getLogger(NettyApplication6.class);


    @Value("${netty.port}")
    private int port;

    @Value("${netty.host}")
    private String host;

    @Autowired
    private NettyServer nettyServer;

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(NettyApplication6.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(host,port);
        ChannelFuture channelFuture = nettyServer.bing(address);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
