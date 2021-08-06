package com.demo.netty.domain;

/**
 * @author wangjianhua
 * @Description 文件分片数据
 * @date 2021/8/6 13:49
 */
public class FileBurstData {

    public FileBurstData() {
    }

    /**
     * 客户端文件地址
     */
    private String fileUrl;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 开始位置
     */
    private Integer beginPos;

    /**
     * 结束位置
     */
    private Integer endPos;

    /**
     * 文件字节 在实际应用中可以使用非对称加密以保证数据传输安全
     */
    private byte[] bytes;

    /**
     * Constants.FileStatus 0开始 1中间 2结尾 3完成
     */
    private Integer status;


    public FileBurstData(Integer status) {
        this.status = status;
    }

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

    public Integer getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(Integer beginPos) {
        this.beginPos = beginPos;
    }

    public Integer getEndPos() {
        return endPos;
    }

    public void setEndPos(Integer endPos) {
        this.endPos = endPos;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
