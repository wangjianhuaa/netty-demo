package com.demo.rpc.network.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17/017 21:38
 **/
public class NetUtil {

    public static boolean isPortUsing(int port){
        boolean flag = false;
        try
        {
            Socket socket = new Socket("localhost",port);
            socket.close();
            flag = true;
        }
        catch (IOException e)
        {

        }
        return flag;
    }

    public static String getHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
