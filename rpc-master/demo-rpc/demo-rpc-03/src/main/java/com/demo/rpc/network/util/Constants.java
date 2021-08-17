package com.demo.rpc.network.util;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17/017 21:31
 **/
public class Constants {

    /**
     * 隐藏的key前缀 隐藏的key前缀只能在filter里拿到 在RpcContext里拿不到 不过可以设置
     */
    public static final char HIDE_KEY_PREFIX = '.';

    /**
     * 内部使用的key前缀 防止和自定义key冲突
     */
    public static final char INTERNAL_KEY_PREFIX = '_';
}
