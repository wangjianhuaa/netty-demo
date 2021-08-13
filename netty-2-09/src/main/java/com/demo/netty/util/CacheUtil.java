package com.demo.netty.util;

import com.demo.netty.server.NettyServer;
import com.demo.netty.domain.ServerInfo;
import io.netty.channel.Channel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 11:29
 */
public class  CacheUtil {

    /**
     * 缓存channel
     */
    public static Map<String, Channel> cacheChannel = Collections.synchronizedMap(new HashMap<String,Channel>());

    /**
     * 缓存服务信息
     */
    public static Map<Integer, ServerInfo> serverInfoMap = Collections.synchronizedMap(new HashMap<Integer, ServerInfo>());

    /**
     * 缓存服务端
     */
    public static Map<Integer, NettyServer> serverMap = Collections.synchronizedMap(new HashMap<Integer, NettyServer>());
}
