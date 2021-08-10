package com.demo.netty.domain;

/**
 * @author wangjianhua
 * @Description 传输协议
 * @date 2021/8/10 11:17
 */
public class TransportProtocol {

    /**
     * 1 用户信息 还可以加入更多 使用type统一维护 或者通过数据库表统一维护
     */
    private Integer type;

    /**
     * 传输对象
     */
    private Object obj;

    public TransportProtocol(Integer type, Object obj) {
        this.type = type;
        this.obj = obj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
