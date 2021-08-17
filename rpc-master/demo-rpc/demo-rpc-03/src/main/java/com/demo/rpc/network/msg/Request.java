package com.demo.rpc.network.msg;

import io.netty.channel.Channel;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 15:45
 */
public class Request {

    private transient Channel channel;

    private String requestId;

    /**
     * 方法
     */
    private String methodName;

    /**
     * 属性
     */
    private Class[] paramTypes;

    /**
     * 入参
     */
    private Object[] args;

    /**
     * 接口
     */
    private String nozzle;

    /**
     * 实现类
     */
    private String ref;

    /**
     * 别名
     */
    private String alias;


    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
