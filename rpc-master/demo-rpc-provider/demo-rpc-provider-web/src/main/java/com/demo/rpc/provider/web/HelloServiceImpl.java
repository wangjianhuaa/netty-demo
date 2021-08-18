package com.demo.rpc.provider.web;

import com.demo.rpc.provider.export.HelloService;
import com.demo.rpc.provider.export.domain.Hi;
import org.springframework.stereotype.Component;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 10:43
 */
@Component("helloService")
public class HelloServiceImpl implements HelloService {
    @Override
    public String hi() {
        return "hi demo rpc";
    }

    @Override
    public String say(String str) {
        return str;
    }

    @Override
    public String sayHi(Hi hi) {
        return hi.getUsername()+ "say:" + hi.getSayMsg();
    }
}
