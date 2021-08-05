package com.demo.netty.util;

import com.demo.netty.domain.MsgBody;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/5 17:46
 */
public class MsgUtil {

    /**
     * 构建protobuf信息体
     */
    public static MsgBody buildMsg(String channelId,String msgInfo){
        MsgBody.Builder msg = MsgBody.newBuilder();
        msg.setChannelId(channelId);
        msg.setMsgInfo(msgInfo);
        return msg.build();
    }
}
