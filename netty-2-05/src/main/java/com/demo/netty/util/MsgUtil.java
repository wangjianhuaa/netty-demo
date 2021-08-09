package com.demo.netty.util;

import com.alibaba.fastjson.JSON;
import com.demo.netty.domian.ServerMsgProtocol;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/9 17:18
 */
public class MsgUtil {

    /**
     * 消息群发
     */
    public static TextWebSocketFrame buildMsgAll(String channelId,String msgInfo){
        //模拟头像
        int i = Math.abs(channelId.hashCode()) % 10;

        ServerMsgProtocol msg = new ServerMsgProtocol();
        // 链接消息 1 自发 2 群发
        msg.setType(2);
        msg.setChannelId(channelId);
        msg.setUserHeadImg("head"+i+".jpg");
        msg.setMsgInfo(msgInfo);
        return new TextWebSocketFrame(JSON.toJSONString(msg));
    }

    /**
     * 私聊
     */
    public static TextWebSocketFrame buildMsgOwner(String channelId){
        ServerMsgProtocol msg = new ServerMsgProtocol();
        // 链接消息 1 自发 2 群发
        msg.setType(1);
        msg.setChannelId(channelId);
        return new TextWebSocketFrame(JSON.toJSONString(msg));
    }
}
