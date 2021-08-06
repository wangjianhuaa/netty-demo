package com.demo.netty.domain;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 9:54
 */
public class MsgInfo {


    private String channelId;

    private String msgContent;

    /**
     * protostuff-runtime 在序列化前需预先传入schema
     * 反序列化不负责对象创建只负责复制 因此必须实体类有一个无参构造器(也就是默认构造器)
     */
    public MsgInfo() {
    }

    public MsgInfo(String channelId, String msgContent) {
        this.channelId = channelId;
        this.msgContent = msgContent;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
