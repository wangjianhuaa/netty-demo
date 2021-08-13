package com.demo.netty.server;

import com.demo.netty.domain.MsgAgreement;
import com.demo.netty.domain.UserChannelInfo;
import com.demo.netty.service.ExtServerService;
import com.demo.netty.util.CacheUtil;
import com.demo.netty.util.MsgUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 14:14
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(MyServerHandler.class);

    private ExtServerService extServerService;

    public MyServerHandler(ExtServerService extServerService) {
        this.extServerService = extServerService;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端。channelId：" + channel.id());
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");

        //保存用户信息
        UserChannelInfo userChannelInfo = new UserChannelInfo(
                channel.localAddress().getHostString(),
                channel.localAddress().getPort(),
                channel.id().toString(),
                new Date());
        extServerService.getRedisUtil().pushObj(userChannelInfo);
        CacheUtil.cacheChannel.put(channel.id().toString(),channel);
        //通知客户端链接建立成功
        String str = "通知客户端链接建立成功" + " " + new Date() + " " + channel.localAddress().getHostString() + "\r\n";
        ctx.writeAndFlush(MsgUtil.buildMsg(channel.id().toString(),str));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
        extServerService.getRedisUtil().remove(ctx.channel().id().toString());
        CacheUtil.cacheChannel.remove(ctx.channel().id().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object objMsgJsonStr) throws Exception {
        //接收msg消息{与上一章节相比，此处已经不需要自己进行解码}
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()) + " 接收到消息内容：" + objMsgJsonStr);
        MsgAgreement msgAgreement = MsgUtil.json2obj(objMsgJsonStr.toString(), MsgAgreement.class);
        String toChannelId = msgAgreement.getToChannelId();
        //判断接收消息用户是否在本服务端
        Channel channel = CacheUtil.cacheChannel.get(toChannelId);
        if(null != channel){
            channel.writeAndFlush(MsgUtil.obj2json(msgAgreement));
            return;
        }
        //为null则说明接收消息的用户不在本服务端 需要全局push消息
        logger.info("接收消息的用户不在本服务端，push========");
        extServerService.push(msgAgreement);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        extServerService.getRedisUtil().remove(ctx.channel().id().toString());
        CacheUtil.cacheChannel.remove(ctx.channel().id().toString(),ctx.channel());
        System.out.println("异常原因:\r\n"+cause.getMessage());
    }
}
