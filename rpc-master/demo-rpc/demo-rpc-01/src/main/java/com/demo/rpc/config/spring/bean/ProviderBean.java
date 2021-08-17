package com.demo.rpc.config.spring.bean;

import com.demo.rpc.config.ProviderConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 10:39
 */
public class ProviderBean extends ProviderConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //发布生产者
        doExport();
    }
}
