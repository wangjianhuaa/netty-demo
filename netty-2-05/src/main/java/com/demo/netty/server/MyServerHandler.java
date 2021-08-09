package com.demo.netty.server;

import ch.qos.logback.core.net.server.Client;
import com.alibaba.fastjson.JSON;
import com.demo.netty.domian.ClientMsgProtocol;
import com.demo.netty.util.ChannelHandler;
import com.demo.netty.util.MsgUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/9 17:25
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger  = LoggerFactory.getLogger(MyServerHandler.class);

    private WebSocketServerHandshaker handshaker;

    /**
     * 活跃状态
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        logger.info("链接报告开始");
        logger.info("链接报告信息：有一客户端链接到本服务端");
        logger.info("链接报告IP:{}", channel.localAddress().getHostString());
        logger.info("链接报告Port:{}", channel.localAddress().getPort());
        logger.info("链接报告完毕");
        ChannelHandler.channelGroup.add(ctx.channel());
    }

    /**
     * 断开链接
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端断开链接{}", ctx.channel().localAddress().toString());
        ChannelHandler.channelGroup.remove(ctx.channel());
    }

    /**
     * 消息读取
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //http
        if(msg instanceof FullHttpRequest){
            FullHttpRequest httpRequest = (FullHttpRequest)msg;

            if(!(httpRequest.decoderResult().isSuccess())){
                DefaultFullHttpResponse httpResponse =
                        new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);

                //返回应答给客户端
                if(httpResponse.status().code()!=200){
                ByteBuf buf = Unpooled.copiedBuffer(httpResponse.status().toString(), CharsetUtil.UTF_8);
                httpResponse.content().writeBytes(buf);
                buf.release();
            }

                ChannelFuture f = ctx.channel().writeAndFlush(httpResponse);
                //如果是非keep-Alive，关闭连接
                if(httpResponse.status().code() !=200){
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
            else{
                handshaker.handshake(ctx.channel(),httpRequest);
            }

            return;
        }

        //ws
        if(msg instanceof WebSocketFrame){

            WebSocketFrame webSocketFrame = (WebSocketFrame)msg;

            //关闭请求
            if(webSocketFrame instanceof CloseWebSocketFrame){
                handshaker.close(ctx.channel(),(CloseWebSocketFrame)webSocketFrame.retain());
                return;
            }

            //ping请求
            if(webSocketFrame instanceof  PingWebSocketFrame){
                ctx.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
                return;
            }

            //  只支持文本消息 不支持二进制格式消息
            if(!(webSocketFrame instanceof TextWebSocketFrame)){
                throw  new Exception("只支持文本格式!");
            }

            String request =((TextWebSocketFrame) webSocketFrame).text();
            logger.info("服务端收到:"+request);

            ClientMsgProtocol clientMsgProtocol = JSON.parseObject(request,ClientMsgProtocol.class);
            //1.请求个人信息

            if(1 == clientMsgProtocol.getType()){
                ctx.channel().writeAndFlush(MsgUtil.buildMsgOwner(ctx.channel().id().toString()));
                return;
            }

            //群发消息
            if(2 == clientMsgProtocol.getType()){
                TextWebSocketFrame textWebSocketFrame = MsgUtil.buildMsgAll
                        (ctx.channel().id().toString(),clientMsgProtocol.getMsgInfo());
                ChannelHandler.channelGroup.writeAndFlush(textWebSocketFrame);

            }
        }
    }

    /**
     * 异常捕获
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.error("异常信息:\r\n"+cause.getMessage());
    }
}
