package com.demo.netty.server.common;

import io.netty.channel.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * @author wangjianhua
 * @Description 抽象类 监控发送速率  获取发送状态
 * @date 2021/8/16 10:08
 */
public abstract class MyServerCommonHandler extends SimpleChannelInboundHandler<String> {

    protected boolean sentFlag;

    private Runnable counterTask;

    private AtomicLong consumeMsgLength = new AtomicLong();

    private long priorProgress;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        counterTask = ()->{
            while (true)
            {
                try
                {
                    Thread.sleep(500);
                    long length = consumeMsgLength.getAndSet(0);
                    if(0 == length){
                        continue;
                    }
                    System.out.println("数据发送速率:"+length+" KB/s");
                }
                catch (InterruptedException ignored)
                {

                }
            }
        };
        super.handlerAdded(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        sendData(ctx);
        //启动监控线程
        new Thread(counterTask).start();
    }

    /**
     * 发送数据
     * @param ctx
     */
    protected abstract void sendData(ChannelHandlerContext ctx);

    protected ChannelProgressivePromise getChannelProgressivePromise(ChannelHandlerContext ctx, Consumer<ChannelProgressiveFuture> completedAction){
        ChannelProgressivePromise channelProgressivePromise = ctx.newProgressivePromise();
        channelProgressivePromise.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception {
                consumeMsgLength.addAndGet(progress-priorProgress);
                priorProgress = progress;
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) throws Exception {
                sentFlag = false;
                if(future.isSuccess()){
                    System.out.println("提醒 消息发送成功=================");
                    priorProgress -= 10;
                    Optional.ofNullable(completedAction).ifPresent(action -> action.accept(future));
                }
                else {
                    System.err.println("提醒 消息发送失败=================");
                    future.cause().printStackTrace();
                }
            }
        });
        return channelProgressivePromise;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("netty server接收到消息:"+msg);
    }
}
