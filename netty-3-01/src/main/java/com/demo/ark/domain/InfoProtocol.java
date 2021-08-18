package com.demo.ark.domain;

/**
 * @author wangjianhua
 * @Description 消息协议
 * @date 2021/8/18 14:41
 */
public class InfoProtocol {

    /**
     * 消息传输给某个管道id
     */
    private String channelId;

    /**
     * 消息类型 1 Text 2 QueryInfoReq 3 FeedBack
     */
    private Integer msgType;

    /**
     * 消息对象
     */
    private Object msgObj;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Object getMsgObj() {
        return msgObj;
    }

    public void setMsgObj(Object msgObj) {
        this.msgObj = msgObj;
    }
}
