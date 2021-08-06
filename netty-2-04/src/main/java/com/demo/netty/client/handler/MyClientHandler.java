package com.demo.netty.client.handler;

import com.demo.netty.domain.Constants;
import com.demo.netty.domain.FileBurstData;
import com.demo.netty.domain.FileBurstInstruct;
import com.demo.netty.domain.FileTransferProtocol;
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
 * @date 2021/8/6 15:25
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端建立与服务端的活跃通道
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息:本客户端链接到服务端 channelId:" + channel.id());
        System.out.println("链接报告ip:" + channel.localAddress().getHostString());
        System.out.println("链接报告port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开链接" + ctx.channel().localAddress().toString());
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //数据格式验证
        if (!(msg instanceof FileTransferProtocol)) {
            return;
        }

        FileTransferProtocol fileTransferProtocol = (FileTransferProtocol) msg;
        //0 传输文件请求 1 传输文件指令 2传输文件数据
        switch (fileTransferProtocol.getTransferType()) {
            case 1:
                FileBurstInstruct fileBurstInstruct = (FileBurstInstruct) fileTransferProtocol.getTransferObj();
                //Constants.FileStatus ｛0开始、1中间、2结尾、3完成｝
                if (Constants.FileStatus.COMPLETE == fileBurstInstruct.getStatus()) {
                    ctx.flush();
                    ctx.close();
                    System.exit(-1);
                    return;
                }
                FileBurstData fileBurstData = FileUtil
                        .readFile(fileBurstInstruct.getClientFileUrl(), fileBurstInstruct.getReadPosition());
                ctx.writeAndFlush(MsgUtil.buildTransferData(fileBurstData));
                System.out.println(new SimpleDateFormat("yyyy-MM-dd")
                        .format(new Date()) + "demo 客户端传输文件信息 File:" + fileBurstData.getFileName()
                        + "size(byte)" + (fileBurstData.getEndPos() - fileBurstData.getBeginPos()));
                break;
            default:
                break;
        }

        /*
        模拟传输过程中断 场景测试可以注释掉
         */
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd")
//                .format(new Date())+"demo 客户端传输文件信息 [主动断开链接 模拟断点续传]");
//        ctx.flush();
//        ctx.close();
//        System.exit(-1);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息:\r\n"+cause.getMessage());
    }
}
