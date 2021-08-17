package com.demo.rpc.config.spring.bean;

import com.demo.rpc.config.ConsumerConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 11:00
 */
public class ConsumerBean<T> extends ConsumerConfig<T> implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return refer();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
