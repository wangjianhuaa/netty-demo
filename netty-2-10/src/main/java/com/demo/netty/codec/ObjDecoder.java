package com.demo.netty.codec;

import com.demo.netty.domain.protocol.PacketClazzMap;
import com.demo.netty.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author wangjianhua
 * @Description 解码器
 * @date 2021/8/13 18:05
 */
public class   ObjDecoder extends ByteToMessageDecoder
{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() <4 ){
            return;
        }
        in.markReaderIndex();
        int dateLength = in.readInt();
        if(in.readableBytes() < dateLength){
            in.resetReaderIndex();
            return;
        }
        //读取指令
        byte command = in.readByte();
        byte[] data  = new byte[dateLength-1];
        in.readBytes(data);
        out.add(SerializationUtil.deserialize(data, PacketClazzMap.packetTypeMap.get(command)));
    }
}
