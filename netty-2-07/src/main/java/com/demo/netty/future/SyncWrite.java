package com.demo.netty.future;

import com.demo.netty.msg.Request;
import com.demo.netty.msg.Response;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 15:11
 */
public class SyncWrite {

        public Response writeAndSync(final Channel channel, final Request request,final long timeout) throws Exception{

        if(channel == null)
        {
          throw new NullPointerException("channel is null");
        }
        if(request == null)
        {
            throw new NullPointerException("request is null");
        }
        if(timeout <= 0)
        {
            //不合法参数
            throw new IllegalArgumentException("timeout <=0");
        }

        String requestId = UUID.randomUUID().toString();
        request.setRequestId(requestId);


        WriteFuture<Response> future = new SyncWriteFuture(request.getRequestId());
        SyncWriteMap.syncKey.put(request.getRequestId(),future);

        Response response = doWriteAndSync(channel,request,timeout,future);

        SyncWriteMap.syncKey.remove(request.getRequestId());
        return response;
    }

    private Response doWriteAndSync
            (final Channel channel
                    ,final Request request
                    ,final long timeout
                    ,final WriteFuture<Response> writeFuture) throws Exception{
            channel.writeAndFlush(request).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    writeFuture.setWriteResult(future.isSuccess());
                    writeFuture.setCause(future.cause());
                    //失败移除
                    if(!writeFuture.isWriteSuccess()){
                        SyncWriteMap.syncKey.remove(writeFuture.requestId());
                    }
                }
            });

            Response response = writeFuture.get(timeout, TimeUnit.MILLISECONDS);
            if(response == null){
                if(writeFuture.isTimeout()){
                    throw new TimeoutException();
                }
                else {
                    throw new Exception(writeFuture.cause());
                }
            }
            return response;
    }
}
