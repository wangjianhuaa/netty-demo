package com.demo.rpc.provider.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wangjianhua
 * @Description 单元测试
 * @date 2021/8/18 10:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class ProviderTest {


    @Test
    public void  init() throws InterruptedException {
        System.out.println("init===========");
        while (true)
        {
            Thread.sleep(500);
        }
    }
}
