package com.zdatbit.redis.study.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private static JedisPool pool;
    private static String host = "192.168.158.128";
    private static int port = 6379;
    private static String password = "zdatbit";

    /**
     * redis全局配置
     */
    static{
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(5);
        pool = new JedisPool(config,host,port);
    }

    /**
     * 从池中获取redis
     * @return
     */
    public static Jedis getJedisFromPool(){
        Jedis jedis = pool.getResource();
        jedis.auth(password);
        return jedis;
    }


    public static void closeRedis(Jedis jedis){
        jedis.close();
    }


    public static Jedis getJedis(){
        Jedis jedis = new Jedis(host,port);
        jedis.auth(password);
        return jedis;
    }

}
