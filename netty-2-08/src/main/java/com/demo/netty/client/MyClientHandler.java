package com.demo.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/11 9:39
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端主动链接服务端
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：本客户端链接到服务端。channelId：" + channel.id());
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
    }

    /**
     * 客户端与服务端断开链接 这里进行重连操作
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开链接重连"+ctx.channel().localAddress().toString());
        //创建线程池
        ExecutorService executor =
                new ThreadPoolExecutor(1,1,0L,TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(10),Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.DiscardPolicy());
        //断线重连
        executor.execute(()-> {
            {
                try
                {
                    new NettyClient().connect("127.0.0.1",7397);
                    System.out.println("netty demo client start done."+this.getClass());
                    Thread.sleep(500);
                }
                catch (Exception e)
                {
                    System.out.println("netty demo client go reconnect...waiting"+this.getClass());
                }
            }
        });

    }

    /**
     * 消息读取
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()) + " 接收到消息：" + msg);
    }

    /**
     * 异常捕获
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常信息，断开重连：\r\n" + cause.getMessage());
        ctx.close();
    }
}
