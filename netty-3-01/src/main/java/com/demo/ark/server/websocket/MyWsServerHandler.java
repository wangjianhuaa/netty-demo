package com.demo.ark.server.websocket;

import com.alibaba.fastjson.JSON;
import com.demo.ark.domain.InfoProtocol;
import com.demo.ark.util.CacheUtil;
import com.demo.ark.util.MsgUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 15:46
 */
public class MyWsServerHandler  extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(MyWsServerHandler.class);

    private WebSocketServerHandshaker handshaker;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        logger.info("连接报告开始");
        logger.info("链接报告信息：有一客户端链接到本服务端");
        logger.info("链接报告IP:{}", channel.localAddress().getHostString());
        logger.info("链接报告Port:{}", channel.localAddress().getPort());
        logger.info("链接报告完毕");
        CacheUtil.wsChannelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端断开链接{}",ctx.channel().localAddress().toString());
        //移除
        CacheUtil.wsChannelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //http请求
        if(msg instanceof FullHttpRequest){
            FullHttpRequest httpRequest = (FullHttpRequest)msg;
            if(!httpRequest.decoderResult().isSuccess()){
                DefaultFullHttpResponse httpResponse =
                        new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
                //返回应答给客户端
                if(httpResponse.status().code()!= 200){
                    ByteBuf buf  = Unpooled.copiedBuffer(httpResponse.status().toString(), CharsetUtil.UTF_8);
                    httpResponse.content().writeBytes(buf);
                    buf.release();
                }
                //如果是非Keep-Alive 关闭连接
                ChannelFuture f = ctx.channel().writeAndFlush(httpResponse);
                if(httpResponse.status().code()!=200){
                    f.addListener(ChannelFutureListener.CLOSE);
                }
                return;
            }

            WebSocketServerHandshakerFactory wsFactory =
                    new WebSocketServerHandshakerFactory("ws:/"+ctx.channel()+"/websocket",
                            null,false);
            handshaker = wsFactory.newHandshaker(httpRequest);
            if(null == handshaker){
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            }
            else {
                handshaker.handshake(ctx.channel(),httpRequest);
            }
            return;
        }

        //ws
        if(msg instanceof WebSocketFrame){
            WebSocketFrame webSocketFrame = (WebSocketFrame)msg;
            //关闭请求
            if(webSocketFrame instanceof CloseWebSocketFrame){
                handshaker.close(ctx.channel(),(CloseWebSocketFrame) webSocketFrame.retain());
                return;
            }
            //ping请求
            if(webSocketFrame instanceof PingWebSocketFrame){
                ctx.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
                return;
            }
            //只支持文本 不支持二进制消息
            if(!(webSocketFrame instanceof TextWebSocketFrame)){
                throw new Exception("只支持文本格式!");
            }

            String request = ((TextWebSocketFrame)webSocketFrame).text();
            logger.info("服务端收到:{}",request);
            InfoProtocol infoProtocol = JSON.parseObject(request,InfoProtocol.class);
            //socket转发消息
            String channelId = infoProtocol.getChannelId();
            Channel channel = CacheUtil.cacheClientChannel.get(channelId);
            if(null == channel){
                return;
            }
            channel.writeAndFlush(MsgUtil.buildMsg(infoProtocol));

            //websocket消息反馈发送成功
            ctx.writeAndFlush(MsgUtil.buildWsMsgText(ctx.channel().id().toString(),"向下位机传达消息success!"));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        CacheUtil.wsChannelGroup.remove(ctx.channel());
        logger.error("异常信息:\r\n{}",cause.getMessage());
    }
}
