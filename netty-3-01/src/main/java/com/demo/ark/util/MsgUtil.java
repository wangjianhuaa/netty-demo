package com.demo.ark.util;

import com.alibaba.fastjson.JSON;
import com.demo.ark.domain.InfoProtocol;
import com.demo.ark.domain.msgobj.Text;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 14:51
 */
public class MsgUtil {

    public static String buildMsg(InfoProtocol infoProtocol){
        return JSON.toJSONString(infoProtocol) + "\r\n";
    }

    public static InfoProtocol getMsg(String infoProtocolStr){
        return JSON.parseObject(infoProtocolStr,InfoProtocol.class);
    }

    public static TextWebSocketFrame buildWsMsgText(String channelId,String msgInfo){
        InfoProtocol infoProtocol = new InfoProtocol();
        infoProtocol.setChannelId(channelId);
        infoProtocol.setMsgType(1);
        infoProtocol.setMsgObj(new Text(msgInfo));
        return new TextWebSocketFrame(JSON.toJSONString(infoProtocol));
    }
}
