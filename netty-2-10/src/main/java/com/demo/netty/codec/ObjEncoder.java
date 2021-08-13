package com.demo.netty.codec;

import com.demo.netty.domain.protocol.Command;
import com.demo.netty.domain.protocol.Packet;
import com.demo.netty.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author wangjianhua
 * @Description 编码器
 * @date 2021/8/13 18:16
 */
public class ObjEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet in, ByteBuf out) throws Exception {
        byte[] data = SerializationUtil.serialize(in);
        out.writeInt(data.length+1);
        out.writeByte(in.getCommand());
        out.writeBytes(data);
    }
}
