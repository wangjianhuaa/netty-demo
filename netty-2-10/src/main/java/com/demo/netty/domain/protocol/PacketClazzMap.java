package com.demo.netty.domain.protocol;

import com.demo.netty.domain.MsgDemo01;
import com.demo.netty.domain.MsgDemo02;
import com.demo.netty.domain.MsgDemo03;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 17:52
 */
public class PacketClazzMap {

    public static Map<Byte,Class<? extends Packet>> packetTypeMap = new ConcurrentHashMap<>();

    static {
        packetTypeMap.put(Command.Demo01, MsgDemo01.class);
        packetTypeMap.put(Command.Demo02, MsgDemo02.class);
        packetTypeMap.put(Command.Demo03, MsgDemo03.class);
    }
}
