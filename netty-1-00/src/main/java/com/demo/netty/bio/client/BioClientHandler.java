package com.demo.netty.bio.client;

import com.demo.netty.bio.channel.ChannelAdapter;
import com.demo.netty.bio.channel.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description bio client处理器
 * @date 2021/7/27/027 20:18
 **/
public class BioClientHandler extends ChannelAdapter {

    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("链接报告LocalAddress:"+ctx.socket().getLocalAddress());
        ctx.writeAndFlush("bio client msg to u \r\n");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat
                ("yyyy-MM-dd HH:mm:ss").format(new Date())+"接收到信息:"+msg);
        ctx.writeAndFlush("接收信息成功! \r\n");
    }
}
