package com.demo.netty.domain;

import java.util.Date;

/**
 * @author wangjianhua
 * @Description 用户管道信息
 * @date 2021/8/13 10:57
 */
public class UserChannelInfo {

    /**
     * ip
     */
    private String ip;

    /**
     * 端口
     */
    private int port;

    /**
     * 管道id
     */
    private String channelId;

    /**
     * 链接时间
     */
    private Date linkedDate;

    public UserChannelInfo(String ip, int port, String channelId, Date linkedDate) {
        this.ip = ip;
        this.port = port;
        this.channelId = channelId;
        this.linkedDate = linkedDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Date getLinkedDate() {
        return linkedDate;
    }

    public void setLinkedDate(Date linkedDate) {
        this.linkedDate = linkedDate;
    }
}
