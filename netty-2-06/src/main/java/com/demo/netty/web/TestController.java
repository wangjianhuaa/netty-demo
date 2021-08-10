package com.demo.netty.web;

import com.demo.netty.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 13:46
 */
@RestController
public class TestController {


    @Autowired
    private NettyServer nettyServer;

    @GetMapping("localAddress")
    public String localAddress(){
        return "nettyServer localAddress"+nettyServer.getChannel().localAddress();
    }
}
