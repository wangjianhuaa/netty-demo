package com.demo.netty.aio.server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author wangjianhua
 * @Description aio服务端
 * @date 2021/7/27/027 9:49
 */
public class AioServer  extends Thread{

    private AsynchronousServerSocketChannel serverSocketChannel;

    @Override
    public void run() {
        try {
            serverSocketChannel =AsynchronousServerSocketChannel
                    .open(AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(),10));
            serverSocketChannel.bind(new InetSocketAddress(7397));
            System.out.println("netty demo start done.");
            //等待
            CountDownLatch latch =new CountDownLatch(1);
            serverSocketChannel.accept(this,new AioServerChannelInitializer());
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel serverSocketChannel(){
            return serverSocketChannel;
    }

    public static void main(String[] args) {
            new AioServer().start();
    }
}
