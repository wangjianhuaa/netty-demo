package com.demo.netty.util;

import com.demo.netty.domain.*;

/**
 * @author wangjianhua
 * @Description 消息构建工具
 * @date 2021/8/6 14:58
 */
public class MsgUtil {

    /**
     * 构建对象:请求传输文件(客户端)
     * @param fileUrl 文件地址
     * @param fileName 文件名
     * @param fileSize 文件大小
     * @return 文件传输协议
     */
    public static FileTransferProtocol buildRequestTransferFile(String fileUrl,String fileName,Long fileSize){

        FileDescInfo fileDescInfo = new FileDescInfo();
        fileDescInfo.setFileUrl(fileUrl);
        fileDescInfo.setFileName(fileName);
        fileDescInfo.setFileSize(fileSize);

        FileTransferProtocol fileTransferProtocol = new FileTransferProtocol();
        //0 请求传输文件 1 文件传输指令 2 文件传输数据
        fileTransferProtocol.setTransferType(Constants.TransferType.REQUEST);
        fileTransferProtocol.setTransferObj(fileDescInfo);

        return fileTransferProtocol;
    }

    /**
     * 构建对象:文件传输数据(客户端)
     * @param fileBurstData 文件分片数据
     * @return 文件传输协议
     */
    public static FileTransferProtocol buildTransferData(FileBurstData fileBurstData){

        FileTransferProtocol fileTransferProtocol = new FileTransferProtocol();
        fileTransferProtocol.setTransferType(Constants.TransferType.DATA);
        fileTransferProtocol.setTransferObj(fileBurstData);

        return fileTransferProtocol;
    }

    /**
     * 构建对象:文件传输指令(服务端)
     * @param status 0 请求传输文件 1 文件传输指令 2 文件传输数据
     * @param clientFileUrl 客户端文件地址
     * @param readPosition 读取位置
     * @return 传输协议
     */
    public static FileTransferProtocol buildTransferInstruct(Integer status,String clientFileUrl,Integer readPosition){

        FileBurstInstruct fileBurstInstruct = new FileBurstInstruct();
        fileBurstInstruct.setStatus(status);
        fileBurstInstruct.setClientFileUrl(clientFileUrl);
        fileBurstInstruct.setReadPosition(readPosition);

        FileTransferProtocol fileTransferProtocol = new FileTransferProtocol();
        fileTransferProtocol.setTransferType(Constants.TransferType.INSTRUCT);
        fileTransferProtocol.setTransferObj(fileBurstInstruct);
        return fileTransferProtocol;
    }

    /**
     * 构建对象:文件传输指令(服务端)
     * @param fileBurstInstruct 文件分片指令
     * @return 传输协议
     */
    public static FileTransferProtocol buildTransferInstruct(FileBurstInstruct fileBurstInstruct){

        FileTransferProtocol fileTransferProtocol = new FileTransferProtocol();
        fileTransferProtocol.setTransferType(Constants.TransferType.INSTRUCT);
        fileTransferProtocol.setTransferObj(fileBurstInstruct);
        return fileTransferProtocol;
    }
}
