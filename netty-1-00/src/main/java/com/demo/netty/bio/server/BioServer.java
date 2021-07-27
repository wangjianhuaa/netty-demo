package com.demo.netty.bio.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author wangjianhua
 * @Description bio服务端
 * @date 2021/7/27/027 18:27
 */
public class BioServer extends Thread {

    private ServerSocket serverSocket = null;

    @Override
    public void run() {
        try
        {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(7397));
            System.out.println("netty demo start done.");
            while (true){
                Socket socket = serverSocket.accept();
                BioServerHandler handler = new BioServerHandler(socket, StandardCharsets.UTF_8);
                handler.start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
