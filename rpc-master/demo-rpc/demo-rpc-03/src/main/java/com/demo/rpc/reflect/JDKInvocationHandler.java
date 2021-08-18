package com.demo.rpc.reflect;

import com.demo.rpc.network.future.SyncWrite;
import com.demo.rpc.network.msg.Request;
import com.demo.rpc.network.msg.Response;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17/017 21:50
 **/
public class JDKInvocationHandler implements InvocationHandler {

    private Request request;

    public JDKInvocationHandler(Request request) {
        this.request = request;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        //获得方法所有参数
        Class<?>[] parameterTypes = method.getParameterTypes();
        //toString方法
        if("toString".equals(methodName) && parameterTypes.length == 0)
        {
            return request.toString();
        }
        //hashcode方法
        else if("hashCode".equals(methodName) && parameterTypes.length == 0)
        {
            return request.hashCode();
        }
        //equals方法
        else if("equals".equals(methodName) && parameterTypes.length == 1){
            return request.equals(args[0]);
        }
        //设置参数
        request.setMethodName(methodName);
        request.setParamTypes(parameterTypes);
        request.setArgs(args);
        request.setRef(request.getRef());
        Response response = new SyncWrite().writeAndSync(request.getChannel(),request,5000);
        //异步调用
        return response.getResult();
    }
}
