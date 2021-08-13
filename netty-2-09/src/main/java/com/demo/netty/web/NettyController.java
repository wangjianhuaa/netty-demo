package com.demo.netty.web;
import com.alibaba.fastjson.JSON;
import com.demo.netty.domain.EasyResult;
import com.demo.netty.domain.ServerInfo;
import com.demo.netty.domain.UserChannelInfo;
import com.demo.netty.redis.RedisUtil;
import com.demo.netty.server.NettyServer;
import com.demo.netty.service.ExtServerService;
import com.demo.netty.util.CacheUtil;
import com.demo.netty.util.NetUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/13 14:56
 */
@Controller
public class NettyController {

    private Logger logger = LoggerFactory.getLogger(NettyController.class);

    /**
     * 线程池创建
     */
    private final static ExecutorService EXECUTOR =
            new ThreadPoolExecutor(2,10,0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.DiscardPolicy());

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    private ExtServerService extServerService;

    @Autowired
    private RedisUtil redisUtil;

    private NettyServer nettyServer;

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("serverPort",serverPort);
        return "index";
    }

    @RequestMapping("/openNettyServer")
    @ResponseBody
    public EasyResult openNettyServer(){
        try {
            int port = NetUtil.getPort();
            logger.info("启动netty服务,获取可用端口:{}",port);
            nettyServer = new NettyServer(new InetSocketAddress(port),extServerService);
            Future<Channel> future = EXECUTOR.submit(nettyServer);
            Channel channel = future.get();
            if(null == channel){
                throw new RuntimeException("netty server open error,channel is null.");
            }
            while (!channel.isActive())
            {
                logger.info("启动netty服务，循环等待启动...... ");
                Thread.sleep(500);
            }
            CacheUtil.serverInfoMap.put(port,new ServerInfo(NetUtil.getHost(),port,new Date()));
            CacheUtil.serverMap.put(port,nettyServer);
            logger.info("启动netty服务完成:{}",channel.localAddress());
            return EasyResult.buildSuccessResult();
        }
        catch (Exception e){
            logger.error("启动nettyServer失败"+e.getMessage());
            return EasyResult.buildErrResult(e);
        }
    }

    @RequestMapping("/closeNettyServer")
    @ResponseBody
    public EasyResult closeNettyServer(int port){
        try
        {
            logger.error("关闭netty服务开始.端口:{}",port);
            NettyServer nettyServer = CacheUtil.serverMap.get(port);
            if(null == nettyServer){
                CacheUtil.serverMap.remove(port);
                return EasyResult.buildSuccessResult();
            }
            nettyServer.destroy();
            CacheUtil.serverMap.remove(port);
            CacheUtil.serverInfoMap.remove(port);
            logger.info("关闭netty服务完成,port:{}",port);
            return EasyResult.buildSuccessResult();
        }
        catch (Exception e)
        {
            logger.error("关闭netty服务失败.port:{}",port,e);
            return EasyResult.buildErrResult(e);
        }
    }

    @RequestMapping("/queryNettyServerList")
    @ResponseBody
    public Collection<ServerInfo> queryNettyServerList(){
        try
        {
            Collection<ServerInfo> nettyServers = CacheUtil.serverInfoMap.values();
            logger.info("查询服务端列表:{}", JSON.toJSONString(nettyServers));
            return nettyServers;
        }
        catch (Exception e)
        {
            logger.error("查询服务器列表失败",e);
            return null;
        }
    }

    @GetMapping("/queryUserChannelInfoList")
    @ResponseBody
    public Collection<UserChannelInfo> queryUserChannelInfoList(){
        try
        {
            logger.info("查询用户列表信息开始");
            List<UserChannelInfo> userChannelInfoList = redisUtil.popList();
            logger.info("查询用户列表信息完成:{}",JSON.toJSONString(userChannelInfoList));
            return userChannelInfoList;
        }
        catch (Exception e)
        {
            logger.error("查询服用户列表信息失败",e);
            return null;
        }
    }
}
