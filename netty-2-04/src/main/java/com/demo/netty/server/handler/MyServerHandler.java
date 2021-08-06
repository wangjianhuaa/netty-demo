package com.demo.netty.server.handler;

import com.alibaba.fastjson.JSON;
import com.demo.netty.domain.*;
import com.demo.netty.util.CacheUtil;
import com.demo.netty.util.FileUtil;
import com.demo.netty.util.MsgUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 16:41
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端连接到本服务端
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端。channelId：" + channel.id());
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
    }

    /**
     * 客户端断开与服务端的连接
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
    }

    /**
     * 读取消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //数据格式验证
        if(!(msg instanceof FileTransferProtocol)){
            return;
        }

        FileTransferProtocol fileTransferProtocol = (FileTransferProtocol)msg;
        //0传输文件'请求'、1文件传输'指令'、2文件传输'数据'
        switch (fileTransferProtocol.getTransferType()){
            case 0:
                FileDescInfo fileDescInfo = (FileDescInfo)fileTransferProtocol.getTransferObj();

                //断点续传信息，实际应用需要把断点续传信息保存到数据库中
                FileBurstInstruct fileBurstInstructOld = CacheUtil.burstDataMap.get(fileDescInfo.getFileName());
                if(null != fileBurstInstructOld){
                    if(fileBurstInstructOld.getStatus() == Constants.FileStatus.COMPLETE){
                        CacheUtil.burstDataMap.remove(fileDescInfo.getFileName());
                    }
                    //传输完成 删除断点信息
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date())+"netty demo 服务端 接收客户端传输文件请求[断点续传]"
                    + JSON.toJSONString(fileBurstInstructOld));
                    ctx.writeAndFlush(MsgUtil.buildTransferInstruct(fileBurstInstructOld));
                    return;
                }
                //发送消息
                FileTransferProtocol sendFileTransferProtocol =
                        MsgUtil.buildTransferInstruct(
                                Constants.FileStatus.BEGIN,
                                fileDescInfo.getFileUrl(),
                                0);
                ctx.writeAndFlush(sendFileTransferProtocol);
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(new Date())+"netty demo 服务端 接收客户端传输文件请求"
                        + JSON.toJSONString(fileDescInfo));
                break;
            case 2:
                FileBurstData fileBurstData = (FileBurstData)fileTransferProtocol.getTransferObj();
                FileBurstInstruct fileBurstInstruct = FileUtil.writeFile("E://", fileBurstData);

                //保存断点续传信息
                CacheUtil.burstDataMap.put(fileBurstData.getFileName(),fileBurstInstruct);

                ctx.writeAndFlush(MsgUtil.buildTransferInstruct(fileBurstInstruct));
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(new Date())+"netty demo 服务端 接收客户端传输文件数据"
                        + JSON.toJSONString(fileBurstData));

                //传输完成删除断点信息
                if(fileBurstInstruct.getStatus() == Constants.FileStatus.COMPLETE){
                    CacheUtil.burstDataMap.remove(fileBurstData.getFileName());
                }
                break;
            default:
                break;
        }

    }

    /**
     * 异常捕获
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息:\r\n"+cause.getMessage());
    }
}
