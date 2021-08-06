package com.demo.netty.domain;

/**
 * @author wangjianhua
 * @Description 文件描述信息
 * @date 2021/8/6 13:56
 */
public class FileDescInfo {

    private String fileUrl;

    private String fileName;

    private Long fileSize;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
