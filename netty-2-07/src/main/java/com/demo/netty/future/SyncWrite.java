package com.demo.netty.future;

import com.demo.netty.msg.Request;
import com.demo.netty.msg.Response;
import io.netty.channel.Channel;

import java.util.UUID;

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

        return null;
    }
}
