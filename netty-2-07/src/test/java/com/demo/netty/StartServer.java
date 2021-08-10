package com.demo.netty;

import com.demo.netty.server.ServerSocket;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10/010 20:54
 **/
public class StartServer {

    public static void main(String[] args) {
        new Thread(new ServerSocket()).start();
        System.out.println("netty demo server 2-07 start done");
    }
}
