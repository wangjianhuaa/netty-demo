package com.demo.netty.redis;

import com.alibaba.fastjson.JSON;
import com.demo.netty.domain.UserChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 13:52
 */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void pushObj(UserChannelInfo userChannelInfo){
        redisTemplate.opsForHash()
                .put("netty-demo-2-09-user",
                userChannelInfo.getChannelId(),
                JSON.toJSONString(userChannelInfo));
    }

    public List<UserChannelInfo> popList(){
        List<Object> values = redisTemplate.opsForHash().values("netty-demo-2-09-user");
        if(values.isEmpty()){
            return new ArrayList<>();
        }
        List<UserChannelInfo> userChannelInfoList = new ArrayList<>();
        for (Object strJson : values) {
            userChannelInfoList.add(JSON.parseObject(strJson.toString(),UserChannelInfo.class));
        }
        return userChannelInfoList;
    }

    public void remove(String channelId){
        redisTemplate.opsForHash().delete("netty-demo-2-09-user",channelId);
    }

    public void clear(){
        redisTemplate.delete("netty-demo-2-09-user");
    }
}
