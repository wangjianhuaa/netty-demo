package com.demo.rpc.config.spring;

import com.demo.rpc.config.spring.bean.ConsumerBean;
import com.demo.rpc.config.spring.bean.ProviderBean;
import com.demo.rpc.config.spring.bean.ServerBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 10:58
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("consumer",new MyBeanDefinitionParser(ConsumerBean.class));
        registerBeanDefinitionParser("provider",new MyBeanDefinitionParser(ProviderBean.class));
        registerBeanDefinitionParser("server",new MyBeanDefinitionParser(ServerBean.class));
    }
}
