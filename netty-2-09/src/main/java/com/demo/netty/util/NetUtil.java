package com.demo.netty.util;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 11:41
 */
public class NetUtil {

    /**
     * 获取端口 范围从7397依次递增
     * @return 端口
     */
    public static int getPort(){
        int initPort = 7397;
        while (true)
        {
            if(!isPortUsing(initPort)){
                break;
            }
            initPort ++;
        }
        return initPort;
    }


    public static boolean isPortUsing(int port){
        boolean flag = false;
        try
        {
            Socket socket = new Socket("localhost",port);
            socket.close();
            flag = true;
        }
        catch (Exception e)
        {
//            e.printStackTrace();
        }
        return flag;
    }

    public static String getHost() throws UnknownHostException{
        return InetAddress.getLocalHost().getHostAddress();
    }
}
