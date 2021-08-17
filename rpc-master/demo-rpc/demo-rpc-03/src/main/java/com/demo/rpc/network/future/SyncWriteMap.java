package com.demo.rpc.network.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 16:45
 */
public class SyncWriteMap {

    public static Map<String,WriteFuture> syncKey  = new ConcurrentHashMap<String,WriteFuture>();
}