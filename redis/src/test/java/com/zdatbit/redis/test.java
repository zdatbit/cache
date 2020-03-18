package com.zdatbit.redis;

import com.zdatbit.redis.study.util.RedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class test {

    @Test
    public void connect(){
       Jedis jedis = RedisUtil.getJedisFromPool();
       System.out.println(jedis.ping());
    }
}
