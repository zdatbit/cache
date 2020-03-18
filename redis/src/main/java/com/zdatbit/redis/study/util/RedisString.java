package com.zdatbit.redis.study.util;

import redis.clients.jedis.Jedis;

public class RedisString {
    private Jedis jedis;

    public RedisString(){
        jedis = RedisUtil.getJedisFromPool();
    }


    /**
     * set
     * @param key
     * @param value
     */
    public void set(String key,String value){
        jedis.set(key,value);
    }

    /**
     * setnx
     * @param key
     * @param value
     */
    public void setnx(String key,String value){
        jedis.setnx(key,value);
    }

    /**
     * get
     * @param key
     */
    public String get(String key){
        return jedis.get(key);
    }

    /**
     * exists
     * @param key
     */
    public boolean exists(String key){
        return jedis.exists(key);
    }

    /**
     * strlen
     * @param key
     * @return
     */
    public long getLen(String key){
        return jedis.strlen(key);
    }

    /**
     * ttl
     * @param key
     * @return
     */
    public long getTTL(String key){
        return jedis.ttl(key);
    }

    /**
     * expire
     * @param key
     * @param expireTime
     */
    public void expire(String key,int expireTime){
        jedis.expire(key,expireTime);
    }

    /**
     * incr
     * @param key
     * @return
     */
    public long incr(String key){
        return jedis.incr(key);
    }

    /**
     * incrby
     * @param key
     * @param num
     * @return
     */
    public long incrBy(String key,int num){
        return jedis.incrBy(key,num);
    }
}
