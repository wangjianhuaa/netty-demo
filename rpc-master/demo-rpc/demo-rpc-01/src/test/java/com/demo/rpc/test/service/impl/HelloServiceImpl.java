package com.demo.rpc.test.service.impl;

import com.demo.rpc.test.service.HelloService;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 11:31
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void echo() {
        System.out.println("hi demo rpc");
    }
}
