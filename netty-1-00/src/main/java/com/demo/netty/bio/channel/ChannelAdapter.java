package com.demo.netty.bio.channel;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/27/027 17:09
 */
public abstract class ChannelAdapter extends Thread{

    private Socket socket;

    private Charset charset;

    private ChannelHandler channelHandler;

    public ChannelAdapter(Socket socket,Charset charset){
        this.socket = socket;
        while (!socket.isConnected())
        {
            break;
        }
        channelHandler = new ChannelHandler(this.socket,charset);
        channelActive(channelHandler);
    }

    @Override
    public void run() {
        try
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String str;
            while ((str = input.readLine())!=null)
            {
                channelRead(channelHandler,str);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 链接通知的抽象类
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
