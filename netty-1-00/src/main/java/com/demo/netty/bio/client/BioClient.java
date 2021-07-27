package com.demo.netty.bio.client;


import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/27/027 20:22
 **/
public class BioClient {

    public static void main(String[] args) {
        try
        {
            Socket socket = new Socket("192.168.1.11",7397);
            System.out.println("netty demo start done.");
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("GBK"));
            bioClientHandler.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
