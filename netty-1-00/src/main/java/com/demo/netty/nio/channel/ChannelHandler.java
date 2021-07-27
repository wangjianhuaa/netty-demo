package com.demo.netty.nio.channel;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description 套接字处理器
 * @date 2021/7/27/027 20:44
 **/
public class ChannelHandler {

    private SocketChannel channel;

    private Charset charset;

    public ChannelHandler(SocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg){
        try
        {
            byte[] bytes = msg.toString().getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public SocketChannel channel(){
        return channel;
    }
}
