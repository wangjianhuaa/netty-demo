package com.demo.netty.domian;

/**
 * @author wangjianhua
 * @Description 客户端消息传输协议
 * @date 2021/8/9 17:11
 */
public class ClientMsgProtocol {

    /**
     * 1 请求个人信息
     * 2 发送聊天消息
     */
    private int type;

    /**
     * 消息
     */
    private String msgInfo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }
}
