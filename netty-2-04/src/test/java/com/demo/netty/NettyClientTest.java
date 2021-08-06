package com.demo.netty;

import com.demo.netty.client.NettyClient;
import com.demo.netty.domain.FileTransferProtocol;
import com.demo.netty.util.MsgUtil;
import io.netty.channel.ChannelFuture;

import java.io.File;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/6 18:04
 */
public class NettyClientTest {

    public static void main(String[] args) {
        //启动客户端
        ChannelFuture channelFuture = new NettyClient().connect("127.0.0.1",7397);

        //文件信息{文件大于1024kb可以方便断点续传}
        File file  = new File("C:\\Users\\wangjianhua\\Desktop\\字节码编程-小傅哥(公众号：bugstack虫洞栈).pdf");
        FileTransferProtocol fileTransferProtocol =
                MsgUtil.buildRequestTransferFile(file.getAbsolutePath(), file.getName(), file.length());

        //发送信息:请求传输文件
        channelFuture.channel().writeAndFlush(fileTransferProtocol);
    }
}
