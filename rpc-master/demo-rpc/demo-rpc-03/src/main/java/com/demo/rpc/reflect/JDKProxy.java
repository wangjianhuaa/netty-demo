package com.demo.rpc.reflect;

import com.demo.rpc.network.msg.Request;
import com.demo.rpc.network.util.ClassLoaderUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wangjianhua
 * @Description jdk代理
 * @date 2021/8/17/017 21:48
 **/
public class JDKProxy {

    public static <T> T getProxy(Class<T> interfaceClass, Request request){
        InvocationHandler handler = new JDKInvocationHandler(request);
        ClassLoader classLoader = ClassLoaderUtils.getCurrentClassLoader();
        @SuppressWarnings("unchecked")
        T result = (T) Proxy.newProxyInstance(classLoader,new Class[]{interfaceClass},handler);
        return result;
    }
}
