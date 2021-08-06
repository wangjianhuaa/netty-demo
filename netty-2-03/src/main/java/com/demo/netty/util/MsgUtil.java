package com.demo.netty.util;

import com.demo.netty.domain.MsgInfo;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 9:57
 */
public class MsgUtil {

    public static MsgInfo buildMsg(String channelId,String msgContent){
        return new MsgInfo(channelId,msgContent);
    }
}
