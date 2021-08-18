package com.demo.rpc.test;

import com.demo.rpc.provider.export.HelloService;
import com.demo.rpc.provider.export.domain.Hi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 11:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class ConsumerTest {

    @Resource(name = "helloService")
    private HelloService helloService;

    @Test
    public void test(){

        String hi = helloService.hi();
        System.out.println("测试结果:" + hi);

        String say = helloService.say("helloWorld");
        System.out.println("测试结果" + say);

        Hi req = new Hi();
        req.setUsername("小明");
        req.setSayMsg("你好小明");

        String result = helloService.sayHi(req);
        System.out.println("测试结果:"+ result);
    }
}
