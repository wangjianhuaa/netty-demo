package com.demo.rpc.network.test.client;

import com.alibaba.fastjson.JSON;
import com.demo.rpc.network.client.ClientSocket;
import com.demo.rpc.network.future.SyncWrite;
import com.demo.rpc.network.msg.Request;
import com.demo.rpc.network.msg.Response;
import io.netty.channel.ChannelFuture;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 18:31
 */
public class StartClient {

    private static ChannelFuture future;

    public static void main(String[] args) {
        ClientSocket client =  new ClientSocket();
        new Thread(client).start();

        while (true)
        {
            try
            {
              if(null == future){
                  future = client.getFuture();
                  Thread.sleep(500);
                  continue;
              }
              //构建发送参数
              Request request = new Request();
              request.setResult("查询用户信息");
              SyncWrite syncWrite = new SyncWrite();
              Response response = syncWrite.writeAndSync(future.channel(),request,1000);
                System.out.println("调用结果:"+ JSON.toJSONString(response));
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
