package com.demo.ark.web;

import com.alibaba.fastjson.JSON;
import com.demo.ark.domain.Device;
import com.demo.ark.domain.EasyResult;
import com.demo.ark.domain.InfoProtocol;
import com.demo.ark.domain.ServerInfo;
import com.demo.ark.util.CacheUtil;
import com.demo.ark.util.MsgUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 16:49
 */
@Controller
public class NettyController {

    private Logger logger = LoggerFactory.getLogger(NettyController.class);

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/queryNettyServerList")
    @ResponseBody
    public Collection<ServerInfo> queryNettyServerList(){
        try
        {
            Collection<ServerInfo> serverInfos = CacheUtil.serverInfoMap.values();
            logger.info("查询服务器列表:{}", JSON.toJSONString(serverInfos));
            return serverInfos;
        }
        catch (Exception e)
        {
            logger.error("查询服务器失败:{}",e.getMessage());
            return null;
        }
    }

    @RequestMapping("/queryDeviceList")
    @ResponseBody
    public Collection<Device> queryDeviceList(){
        try
        {
            Collection<Device> devices = CacheUtil.deviceGroup.values();
            logger.info("查询设备链接列表:{}",JSON.toJSONString(devices));
            return devices;
        }
        catch (Exception e)
        {
            logger.error("查询设备链接列表失败:{}",e.getMessage());
            return null;
        }
    }

    @RequestMapping("/doSendMsg")
    @ResponseBody
    public EasyResult doSendMsg(String reqStr){
        try
        {
            logger.info("向设备发送消息[可以通过另外一个Web服务调用本接口发送消息]:{}",reqStr);
            InfoProtocol infoProtocol = MsgUtil.getMsg(reqStr);
            String channelId = infoProtocol.getChannelId();
            Channel channel = CacheUtil.cacheClientChannel.get(channelId);
            channel.writeAndFlush(MsgUtil.buildMsg(infoProtocol));
            return EasyResult.buildSuccessResult();
        }
        catch (Exception e)
        {
            logger.error("向设备发送消息失败:{}",reqStr,e);
            return EasyResult.buildErrResult(e);
        }
    }
}
