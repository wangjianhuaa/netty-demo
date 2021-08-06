package com.demo.netty.util;

import com.demo.netty.domain.Constants;
import com.demo.netty.domain.FileBurstData;
import com.demo.netty.domain.FileBurstInstruct;
import com.demo.netty.domain.FileMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 14:18
 */
public class FileUtil {

    /**
     * 文件读取
     * @param fileUrl 文件url
     * @param readPosition 读取位置
     * @return 文件分片数据
     */
    public static FileBurstData readFile(String fileUrl,Integer readPosition) throws IOException {

        File file = new File(fileUrl);
        //RandomAccessFile java自带类 支持文件的定位读取和写入 可满足对文件分片的基本需求
        //FileMode.READ_ONLY r 只读模式
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, FileMode.READ_ONLY);
        //文件记录指针位置
        randomAccessFile.seek(readPosition);
        //规定 1024默认读取范围
        byte[] bytes = new byte[1024*100];
        int readSize = randomAccessFile.read(bytes);
        //如果没读到数据 返回已完成
        if(readSize < 0){
            randomAccessFile.close();
            return new FileBurstData(Constants.FileStatus.COMPLETE);
        }
        FileBurstData fileInfo = new FileBurstData();
        fileInfo.setFileUrl(fileUrl);
        fileInfo.setFileName(file.getName());
        fileInfo.setBeginPos(readPosition);
        fileInfo.setEndPos(readPosition+readSize);
        //不足1024 需要拷贝去掉空字节
        if(readSize < 1024 * 100){
            byte[] copy = new byte[readSize];
            System.arraycopy(bytes,0,copy,0,readSize);
            fileInfo.setBytes(copy);
            //不足1024说明分片读取到了最后一片
            fileInfo.setStatus(Constants.FileStatus.END);
        }
        else {
            fileInfo.setBytes(bytes);
            //够1024说明还在读取中
            fileInfo.setStatus(Constants.FileStatus.CENTER);
        }
        randomAccessFile.close();
        return fileInfo;
    }

    /**
     * 写文件
     * @param baseUrl Url
     * @param fileBurstData 文件分片数据
     * @return 文件分片指令
     * @throws IOException io异常
     */
    public static FileBurstInstruct writeFile(String baseUrl,FileBurstData fileBurstData) throws IOException{

        //如果文件状态已完成 指令返回已完成
        if(Constants.FileStatus.COMPLETE == fileBurstData.getStatus()){
            return new FileBurstInstruct(Constants.FileStatus.COMPLETE);
        }
        //这里用File.separator保证各系统兼容性
        File file = new File(baseUrl+File.separator+fileBurstData.getFileName());
//        File file = new File(fileBurstData.getFileUrl());
        //FileMode.READ_AND_WRITE rw 读写模式
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,FileMode.READ_AND_WRITE);
        //移动文件记录指针位置
        randomAccessFile.seek(fileBurstData.getBeginPos());
        //调用了seek(start)方法 是指把文件的记录指针定位到start字节的位置，也就是说程序将从start字节开始写数据
        randomAccessFile.write(fileBurstData.getBytes());
        randomAccessFile.close();

        if(Constants.FileStatus.END == fileBurstData.getStatus()){
            return new FileBurstInstruct(Constants.FileStatus.COMPLETE);
        }

        //文件分片传输指令
        FileBurstInstruct fileBurstInstruct = new FileBurstInstruct();
        fileBurstInstruct.setStatus(Constants.FileStatus.CENTER);
        fileBurstInstruct.setClientFileUrl(fileBurstData.getFileUrl());
        //设置下一片的读取位置
        fileBurstInstruct.setReadPosition(fileBurstData.getEndPos()+1);
        return fileBurstInstruct;
    }
}
