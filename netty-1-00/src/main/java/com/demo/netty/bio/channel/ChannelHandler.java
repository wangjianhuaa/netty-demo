package com.demo.netty.bio.channel;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/27/027 17:14
 */
public class ChannelHandler {
    private Socket socket;

    private Charset charset;

    public ChannelHandler(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg){
        OutputStream out = null;
        try
        {
            out = socket.getOutputStream();
            out.write((msg.toString()).getBytes(charset));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public Socket socket() {
        return socket;
    }
}
