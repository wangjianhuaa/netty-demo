package com.demo.rpc.provider.export;

import com.demo.rpc.provider.export.domain.Hi;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 10:34
 */
public interface HelloService {

    String hi();

    String say(String str);

    String sayHi(Hi hi);
}
