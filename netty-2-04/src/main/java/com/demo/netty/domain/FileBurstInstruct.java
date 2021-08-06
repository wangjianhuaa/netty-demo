package com.demo.netty.domain;

/**
 * @author wangjianhua
 * @Description 文件分片指令
 * @date 2021/8/6 13:54
 */
public class FileBurstInstruct {

    public FileBurstInstruct() {
    }

    private Integer status;

    private String clientFileUrl;

    private Integer readPosition;


    public FileBurstInstruct(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClientFileUrl() {
        return clientFileUrl;
    }

    public void setClientFileUrl(String clientFileUrl) {
        this.clientFileUrl = clientFileUrl;
    }

    public Integer getReadPosition() {
        return readPosition;
    }

    public void setReadPosition(Integer readPosition) {
        this.readPosition = readPosition;
    }
}
