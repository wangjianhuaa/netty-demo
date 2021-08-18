package com.demo.ark.server.socket;

import com.demo.ark.domain.Device;
import com.demo.ark.util.CacheUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 15:07
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(MyServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel)ctx.channel();
        String channelId = channel.id().toString();
        logger.info("链接报告开始");
        logger.info("链接报告信息：有一客户端链接到本服务端。channelId：" + channelId);
        logger.info("链接报告IP:" + channel.localAddress().getHostString());
        logger.info("链接报告Port:" + channel.localAddress().getPort());
        logger.info("链接报告完毕");

        //构建设备信息(下位机 中继器 IO板卡)
        Device device = new Device();
        device.setChannelId(channelId);
        device.setNumber(UUID.randomUUID().toString());
        device.setIp(channel.remoteAddress().getHostString());
        device.setPort(channel.remoteAddress().getPort());
        device.setConnectTime(new Date());
        //添加设备信息
        CacheUtil.deviceGroup.put(channelId,device);
        CacheUtil.cacheClientChannel.put(channelId,channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端断开链接：{}", ctx.channel().localAddress().toString());
        String channelId = ctx.channel().id().toString();
        //移除设备信息
        CacheUtil.deviceGroup.remove(channelId);
        CacheUtil.cacheClientChannel.remove(channelId);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object objMsgJsonStr) throws Exception {
        //接收设备发来消息
        logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date())+"接收到消息"+objMsgJsonStr);

        //转发消息到WS
        CacheUtil.wsChannelGroup.writeAndFlush(new TextWebSocketFrame(objMsgJsonStr.toString()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String channelId = ctx.channel().id().toString();
        //移除设备信息
        CacheUtil.deviceGroup.remove(channelId);
        CacheUtil.cacheClientChannel.remove(channelId);
        ctx.close();
        logger.error("异常信息:{}",cause.getMessage());
    }
}
