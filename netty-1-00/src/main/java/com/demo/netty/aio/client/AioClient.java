package com.demo.netty.aio.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

/**
 * @author wangjianhua
 * @Description 客户端
 * @date 2021/7/27/027 16:40
 */
public class AioClient {
    public static void main(String[] args) throws Exception {
            AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        Future<Void> future = socketChannel.connect(new InetSocketAddress("172.17.224.1", 7397));
        System.out.println("netty demo client start done.");
        future.get();
        socketChannel.read(ByteBuffer.allocate(1024),
                null,
                new AioClientHandler(socketChannel, Charset.forName("GBK")));
        Thread.sleep(1000);
    }
}
