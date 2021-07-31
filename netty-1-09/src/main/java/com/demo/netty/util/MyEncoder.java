package com.demo.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author wangjianhua
 * @Description 自定义编码器
 * @date 2021/7/31/031 20:05
 **/
public class MyEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf out) throws Exception {
        String msg =in.toString();
        byte[] bytes = msg.getBytes();

        byte[] send = new byte[bytes.length+2];
        System.arraycopy(bytes,0,send,1,bytes.length);
        //协议开始标志
        send[0] = 0x02;
        //协议结束标志
        send[send.length-1] = 0x03;

        out.writeInt(send.length);
        out.writeBytes(send);
    }
}
