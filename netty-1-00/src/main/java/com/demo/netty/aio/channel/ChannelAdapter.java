package com.demo.netty.aio.channel;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author wangjianhua
 * @Description 模仿netty的Channel适配器
 * @date 2021/7/26/026 19:53
 **/
public abstract class ChannelAdapter implements CompletionHandler<Integer,Object> {

    private AsynchronousSocketChannel channel;

    private Charset charset;


    public ChannelAdapter(AsynchronousSocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
        if(channel.isOpen()){
            channelActive(new ChannelHandler(channel,charset));
        }
    }

    @Override
    public void completed(Integer result, Object attachment) {
        try{
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            final long timeout = 60*60L;
            channel.read(buffer, timeout, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    if(result==-1){
                        try {
                            channelInActive(new ChannelHandler(channel,charset));
                            channel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    buffer.flip();
                    channelRead(new ChannelHandler(channel,charset),charset.decode(buffer));
                    buffer.clear();
                    channel.read(buffer,timeout,TimeUnit.SECONDS,null,this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    exc.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.getStackTrace();
    }

    public abstract void channelActive(ChannelHandler ctx);

    public abstract void channelInActive(ChannelHandler ctx);

    /**
     * 读取消息抽象类
     */
    public abstract void channelRead(ChannelHandler ctx,Object msg);
}
