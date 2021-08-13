package com.demo.netty.util;

import com.demo.netty.domain.MsgDemo01;
import com.demo.netty.domain.MsgDemo02;
import com.demo.netty.domain.MsgDemo03;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 18:13
 */
public class MsgUtil {

    public static MsgDemo01 buildMsgDemo01(String channelId,String msgContent){
        return new MsgDemo01(channelId,msgContent);
    }

    public static MsgDemo02 buildMsgDemo02(String channelId,String msgContent){
        return new MsgDemo02(channelId,msgContent);
    }

    public static MsgDemo03 buildMsgDemo03(String channelId, String msgContent){
        return new MsgDemo03(channelId,msgContent);
    }
}
