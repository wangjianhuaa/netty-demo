package com.demo.netty.bio.server;



import com.demo.netty.bio.channel.ChannelAdapter;
import com.demo.netty.bio.channel.ChannelHandler;

import java.net.Socket;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description bio服务端处理器
 * @date 2021/7/27/027 18:16
 */
public class BioServerHandler extends ChannelAdapter {

    public BioServerHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try
        {
            System.out.println("链接报告LocalAddress"+ctx.socket().getLocalAddress());
            ctx.writeAndFlush("Bio server msg== \r\n");
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"接收消息"+msg);
        ctx.writeAndFlush("bio接收消息成功! \r\n");
    }
}
