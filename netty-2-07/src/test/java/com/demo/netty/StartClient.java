package com.demo.netty;

import com.alibaba.fastjson.JSON;
import com.demo.netty.client.ClientSocket;
import com.demo.netty.future.SyncWrite;
import com.demo.netty.msg.Request;
import com.demo.netty.msg.Response;
import io.netty.channel.ChannelFuture;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10/010 20:49
 **/
public class StartClient {

    private static ChannelFuture future;

    public static void main(String[] args) {
        ClientSocket client = new ClientSocket();
        new Thread(client).start();

        while (true) {
            try
            {
                //获取future 线程有等待处理时间
                if(null == future){
                    future = client.getFuture();
                    Thread.sleep(500);
                    continue;
                }
                //构建发送参数
                Request request = new Request();
                request.setResult("查询用户信息");
                SyncWrite s = new SyncWrite();
                Response response = s.writeAndSync(future.channel(), request, 1000);
                System.out.println("调用结果"+ JSON.toJSON(response));
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        }
    }

