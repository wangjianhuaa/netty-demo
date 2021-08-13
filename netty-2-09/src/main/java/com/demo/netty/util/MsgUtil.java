package com.demo.netty.util;

import com.alibaba.fastjson.JSON;
import com.demo.netty.domain.MsgAgreement;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 11:37
 */
public class MsgUtil {

    public static MsgAgreement buildMsg(String channelId,String content){
        return new MsgAgreement(channelId,content);
    }

    public static MsgAgreement json2obj(String objJsonStr, Class<MsgAgreement> msgAgreementClass){
        return JSON.parseObject(objJsonStr,msgAgreementClass);
    }

    public static String obj2json(MsgAgreement msgAgreement){
        return JSON.toJSONString(msgAgreement)+"\r\n";
    }
}
