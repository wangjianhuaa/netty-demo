package com.demo.netty.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 18:10
 */
public class SyncWriteMap {

    public static Map<String,WriteFuture> syncKey = new ConcurrentHashMap<>();
}
