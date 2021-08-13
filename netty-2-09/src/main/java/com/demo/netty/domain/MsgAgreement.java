package com.demo.netty.domain;

/**
 * @author wangjianhua
 * @Description 消息协议
 * @date 2021/8/13 10:53
 */
public class MsgAgreement {

    /**
     * 要发送的channelId
     */
    private String toChannelId;

    /**
     * 消息内容
     */
    private String content;

    public MsgAgreement(String toChannelId, String content) {
        this.toChannelId = toChannelId;
        this.content = content;
    }

    public String getToChannelId() {
        return toChannelId;
    }

    public void setToChannelId(String toChannelId) {
        this.toChannelId = toChannelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
