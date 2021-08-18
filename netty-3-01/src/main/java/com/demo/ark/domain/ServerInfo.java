package com.demo.ark.domain;

import java.util.Date;

/**
 * @author wangjianhua
 * @Description 服务端信息
 * @date 2021/8/18 14:44
 */
public class ServerInfo {

    /**
     * 服务类型
     */
    private String typeInfo;

    /**
     * ip
     */
    private String ip;

    /**
     * 端口
     */
    private int port;

    /**
     * 启动时间
     */
    private Date openDate;


    public ServerInfo(String typeInfo, String ip, int port, Date openDate) {
        this.typeInfo = typeInfo;
        this.ip = ip;
        this.port = port;
        this.openDate = openDate;
    }

    public String getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
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

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
}
