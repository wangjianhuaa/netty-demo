package com.demo.ark;

import com.demo.ark.domain.ServerInfo;
import com.demo.ark.server.socket.NettyServer;
import com.demo.ark.server.websocket.WsNettyServer;
import com.demo.ark.util.CacheUtil;
import com.demo.ark.util.NetUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 17:01
 */
@SpringBootApplication(scanBasePackages = "com.demo.ark")
public class Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(Application.class);

    @Value("${netty.socket.port}")
    private int nettyServerPort;

    @Value("${netty.websocket.port}")
    private int nettyWebSocketPort;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    /**
     * 构建线程池
     */
    ExecutorService executorService =
            new ThreadPoolExecutor(2,2,0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.DiscardPolicy());

    @Override
    public void run(String... args) throws Exception {
        //启动nettyServer-socket
        logger.info("启动nettyServer服务 启动端口:{}",nettyServerPort);
        NettyServer nettyServer = new NettyServer(new InetSocketAddress(nettyServerPort));
        Future<Channel> future = executorService.submit(nettyServer);
        Channel channel = future.get();
        if(null == channel){
            throw  new RuntimeException("netty server open error channel is null");
        }
        while (!channel.isActive()){
            logger.info("启动netty服务，循环等待启动...");
            Thread.sleep(500);
        }
        logger.info("启动netty server服务 完成:{}",channel.localAddress());
        CacheUtil.serverInfoMap.put(nettyServerPort,new ServerInfo("NettySocket", NetUtil.getHost(),nettyServerPort,new Date()));


        //启动nettyServer-WebSocket
        logger.info("启动nettyWsServer服务 启动端口:{}",nettyWebSocketPort);
        WsNettyServer wsNettyServer = new WsNettyServer(new InetSocketAddress(nettyWebSocketPort));
        Future<Channel> wsFuture = executorService.submit(wsNettyServer);
        Channel wsChannel = wsFuture.get();
        if(null == wsChannel){
            throw new RuntimeException("netty server-ws open error channel is null");
        }
        while (!wsChannel.isActive()){
            logger.info("启动nettyWsServer服务，循环等待启动...");
            Thread.sleep(500);
        }
        logger.info("启动NettyWsServer服务 完成:{}",wsChannel.localAddress());
        CacheUtil.serverInfoMap.put(nettyWebSocketPort,new ServerInfo("NettyWsSocket",NetUtil.getHost(),nettyWebSocketPort,new Date()));
    }
}
