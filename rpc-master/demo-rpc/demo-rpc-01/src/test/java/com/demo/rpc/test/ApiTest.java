package com.demo.rpc.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 11:34
 */
public class ApiTest {

    public static void main(String[] args) {
        String[] configs = {"demo-rpc-consumer.xml","demo-rpc-provider.xml"};
        new ClassPathXmlApplicationContext(configs);
    }
}
