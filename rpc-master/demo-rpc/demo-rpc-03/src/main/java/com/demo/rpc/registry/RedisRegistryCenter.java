package com.demo.rpc.registry;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wangjianhua
 * @Description rpc注册中心
 * @date 2021/8/17/017 22:04
 **/
public class RedisRegistryCenter {


    /**
     * 非切片客户端连接
     */
    public static Jedis jedis;

    /**
     * redis连接池初始化
     *
     * @param host ip
     * @param port 端口
     */
    public static void init(String host, int port) {
        //jedis 连接池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);
        JedisPool jedisPool = new JedisPool(config, host, port);
        jedis = jedisPool.getResource();
    }

    /**
     * 注册生产者
     *
     * @param nozzle 借口
     * @param alias  别名
     * @param info   信息
     * @return 注册结果
     */
    public static Long registryProvider(String nozzle, String alias, String info) {
        return jedis.sadd(nozzle + "_" + alias, info);
    }

    public static String obtainProvider(String nozzle, String alias) {
        return jedis.srandmember(nozzle + "_" + alias);
    }

    public static Jedis jedis() {
        return jedis;
    }
}
