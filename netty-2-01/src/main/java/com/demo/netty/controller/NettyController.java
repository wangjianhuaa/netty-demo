package com.demo.netty.controller;

import com.demo.netty.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/4 15:17
 */
@RestController
@RequestMapping("nettyserver")
public class NettyController {

    @Autowired
    private NettyServer nettyServer;

    @GetMapping("localAddress")
    public String localAddress(){
        return "nettyServer localAddress"+nettyServer.getChannel().localAddress();
    }

    @GetMapping("isOpen")
    public String isOpen(){
        return "nettyServer isOpen"+nettyServer.getChannel().isOpen();
    }
}
