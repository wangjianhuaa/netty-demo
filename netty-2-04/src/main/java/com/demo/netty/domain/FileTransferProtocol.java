package com.demo.netty.domain;

/**
 * @author wangjianhua
 * @Description 文件传输协议
 * @date 2021/8/6 13:58
 */
public class FileTransferProtocol {

    /**
     * 0 请求传输文件 1 文件传输指令 2 文件传输数据
     */
    private Integer transferType;

    /**
     * 数据对象 0 FileDescInfo 1 FileBurstInstruct 2 FileBurstData
     */
    private Object transferObj;

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public Object getTransferObj() {
        return transferObj;
    }

    public void setTransferObj(Object transferObj) {
        this.transferObj = transferObj;
    }
}
