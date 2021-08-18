package com.demo.ark.test;

import com.alibaba.fastjson.JSON;
import com.demo.ark.domain.InfoProtocol;
import com.demo.ark.domain.msgobj.Feedback;
import com.demo.ark.domain.msgobj.QueryInfoReq;
import com.demo.ark.util.MsgUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description 下位机模拟
 * @date 2021/8/18 17:25
 */
public class ApiTest {

    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try
        {
            final Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ,true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {

                            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
                            channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
                            channel.pipeline().addLast(new ChannelInboundHandlerAdapter(){

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object objMsgJsonStr) throws Exception {
                                    System.out.println(objMsgJsonStr.toString());
                                    InfoProtocol msg = MsgUtil.getMsg(objMsgJsonStr.toString());

                                    Integer msgType = msg.getMsgType();

                                    if(2 != msgType){
                                        return;
                                    }

                                    String queryInfoReqStr = msg.getMsgObj().toString();
                                    QueryInfoReq queryInfoReq = JSON.parseObject(queryInfoReqStr,QueryInfoReq.class);

                                    Integer stateType = queryInfoReq.getStateType();

                                    Feedback feedBack = null;
                                    //查询温度信息
                                    if(1 == stateType){
                                        feedBack = new Feedback(ctx.channel().id().toString(),
                                                1,"温度信息:"+(double)(Math.random()*100)+"°C");
                                    }
                                    //查询光谱数据
                                    else if (2 == stateType)
                                    {
                                        feedBack = new Feedback(ctx.channel().id().toString(),
                                                2,"光谱数据:"+(int)(Math.random()*100)
                                                + "-" + (int)(Math.random() * 100) + "-" + (int)(Math.random() * 100)
                                                + "-" + (int)(Math.random() * 100));
                                    }

                                    InfoProtocol infoProtocol = new InfoProtocol();
                                    infoProtocol.setChannelId(ctx.channel().id().toString());
                                    infoProtocol.setMsgType(3);
                                    infoProtocol.setMsgObj(feedBack);

                                    ctx.writeAndFlush(JSON.toJSONString(infoProtocol)+ "\r\n");
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    System.err.println(cause.getMessage());
                                }
                            });
                        }
                    });
            ChannelFuture f= b.connect("127.0.0.1",7397).sync();
            System.out.println("模拟下位机 启动成功 =============");
            f.channel().closeFuture().syncUninterruptibly();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            workerGroup.shutdownGracefully();
        }
    }
}

