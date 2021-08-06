package com.demo.netty.util;

import com.demo.netty.domain.FileBurstInstruct;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 14:16
 */
public class CacheUtil {

    public static Map<String, FileBurstInstruct> burstDataMap = new ConcurrentHashMap<>();
}
