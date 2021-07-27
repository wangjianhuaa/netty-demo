package com.demo.netty.nio.channel;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wangjianhua
 * @Description nio套接字适配器
 * @date 2021/7/27/027 20:43
 **/
public abstract class ChannelAdapter extends Thread {

    private Selector selector;

    private ChannelHandler channelHandler;

    private Charset charset;

    public ChannelAdapter(Selector selector,Charset charset){
        this.selector = selector;
        this.charset = charset;
    }

    @Override
    public void run() {
        while (true)
        {
            try
            {
                selector.select(1000);
                Set<SelectionKey> selectedKeys  = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext())
                {
                    key = it.next();
                    it.remove();
                    handleInput(key);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException{
        if(!key.isValid())
        {
            return;
        }
        //客户端SocketChannel
        Class<?> superclass = key.channel().getClass().getSuperclass();
        if(superclass == SocketChannel.class)
        {
            SocketChannel socketChannel = (SocketChannel)key.channel();
            if(key.isConnectable())
            {
                if(socketChannel.finishConnect())
                {
                    channelHandler = new ChannelHandler(socketChannel,charset);
                    channelActive(channelHandler);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }
                else
                {
                    System.exit(1);
                }
            }

        }
        if(superclass == ServerSocketChannel.class)
        {
            if(key.isAcceptable())
            {
               ServerSocketChannel serverSocketChannel =  (ServerSocketChannel)key.channel();
               SocketChannel socketChannel = serverSocketChannel.accept();
               socketChannel.configureBlocking(false);
               socketChannel.register(selector,SelectionKey.OP_READ);

               channelHandler = new ChannelHandler(socketChannel,charset);
               channelActive(channelHandler);
            }
        }

        if(key.isReadable())
        {
            SocketChannel socketChannel = (SocketChannel)key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int readBytes = socketChannel.read(readBuffer);
            if(readBytes>0)
            {
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                channelRead(channelHandler,new String(bytes,charset));
            }
            else if(readBytes<0)
            {
                key.cancel();
                socketChannel.close();
            }
        }
    }

    /**
     * 链接通知抽象类
     * @param ctx 套接字处理器
     */
    public abstract void channelActive(ChannelHandler ctx);

    /**
     * 读取消息抽象类
     * @param ctx 套接字处理器
     * @param msg 消息
     */
    public abstract void channelRead(ChannelHandler ctx,Object msg);
}
