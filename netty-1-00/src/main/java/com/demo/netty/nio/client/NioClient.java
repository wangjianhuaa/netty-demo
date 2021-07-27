package com.demo.netty.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description nio 客户端
 * @date 2021/7/27/027 21:23
 **/
public class NioClient {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        boolean isConnect = socketChannel.connect(new InetSocketAddress("192.168.1.11", 7397));
        if(isConnect)
        {
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
        else
        {
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }

        System.out.println("nio client netty demo start done.");
        new NioClientHandler(selector, Charset.forName("GBK")).start();
    }
}
