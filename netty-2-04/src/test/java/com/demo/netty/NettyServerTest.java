package com.demo.netty;

import com.demo.netty.server.NettyServer;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 18:10
 */
public class NettyServerTest {

    public static void main(String[] args) {
        new NettyServer().bing(7397);
    }
}
