package com.demo.ark.util;

import com.demo.ark.domain.Device;
import com.demo.ark.domain.ServerInfo;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 14:56
 */
public class CacheUtil {

    /**
     * 用于存放用户Channel信息 也可以建立map结构模拟不同的消息群
     */
    public static ChannelGroup wsChannelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 缓存服务信息
     */
    public static Map<Integer, ServerInfo> serverInfoMap = Collections.synchronizedMap(new HashMap<Integer, ServerInfo>());

    /**
     * 缓存用户 cacheClientChannel channelId -> Channel
     */
    public static Map<String, Channel> cacheClientChannel = Collections.synchronizedMap(new HashMap<String,Channel>());

    /**
     * 设备组
     */
    public static Map<String, Device> deviceGroup = Collections.synchronizedMap(new HashMap<String, Device>());
}
