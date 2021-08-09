package com.demo.netty.domian;

/**
 * @author wangjianhua
 * @Description 客户端消息传输协议
 * @date 2021/8/9 17:13
 */
public class ServerMsgProtocol {

    /**
     * 链接消息
     * 1 自发消息
     * 2 群发消息
     */
    private int type;

    /**
     * 通信管道id 实际使用会映射为用户名
     */

    private String channelId;

    /**
     * 用户头像 模拟分配 实际应该从数据库拿
     */

    private String userHeadImg;

    /**
     * 通信消息
     */
    private String msgInfo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }
}
