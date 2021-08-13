package com.demo.netty.domain.protocol;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 17:48
 */
public abstract class Packet {

    /**
     * 获取协议指令
     * @return 返回指令值
     */
    public abstract Byte getCommand();
}
