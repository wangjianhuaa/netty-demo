package com.demo.netty;

import com.demo.netty.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 11:03
 */
@SpringBootApplication(scanBasePackages = "com.demo.netty")
public class Application implements CommandLineRunner {


    @Autowired
    private RedisUtil redisUtil;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        redisUtil.clear();
    }
}
