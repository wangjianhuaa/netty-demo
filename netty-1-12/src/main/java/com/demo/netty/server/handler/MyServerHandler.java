package com.demo.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/4 11:33
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(msg instanceof HttpRequest){
            DefaultHttpRequest  request = (DefaultHttpRequest)msg;
            System.out.println("URI:"+request.getUri());
            System.err.println(msg);
        }

        if(msg instanceof HttpContent){
            LastHttpContent httpContent = (LastHttpContent)msg;
            ByteBuf byteData = httpContent.content();
            if(!(byteData instanceof EmptyByteBuf)){
                //接受msg消息
                byte[] msgByte = new byte[byteData.readableBytes()];
                byteData.readBytes(msgByte);
                System.out.println(new String(msgByte, StandardCharsets.UTF_8));
            }
        }

        String sendMsg = "DBeaver是一款优秀的数据库管理工具，支持管理众多数据库产品，巴拉巴拉。\n" +
                "DBeaver Enterprise（简称DBeaverEE）支持MongoDB、Redis、Apache Hive等，但是需要付费使用。\n" +
                "这次要送的这份礼就是： DBeaverEE 21.0.0及以下版本（理论上适用于目前所有新老版本）的破解，可使用它来激活你手头上的DBeaverEE。";
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(sendMsg.getBytes(StandardCharsets.UTF_8)));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
        ctx.write(response);
        ctx.flush();
    }

    /**
     * 消息读取完成操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 异常抓捕
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
