package com.demo.rpc.network.test.server;

import com.demo.rpc.network.server.ServerSocket;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 18:31
 */
public class StartServer {
    public static void main(String[] args) {
        System.out.println("启动服务端开始");
        new Thread(new ServerSocket()).start();
        System.out.println("启动服务端结束");
    }
}
