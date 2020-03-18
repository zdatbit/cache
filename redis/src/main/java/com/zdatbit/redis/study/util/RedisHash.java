package com.zdatbit.redis.study.util;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

public class RedisHash {

    private Jedis jedis;

    public RedisHash(){
        jedis = RedisUtil.getJedisFromPool();
    }

    /**
     * set
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key,String field,String value){
        jedis.hset(key,field,value);
    }

    /**
     * hget
     * @param key
     * @param field
     * @return
     */
    public String hget(String key,String field){
        return jedis.hget(key,field);
    }

    /**
     * hgetall
     * @param key
     * @return
     */
    public Map<String,String> hgetAll(String key){
        return jedis.hgetAll(key);
    }

    /**
     * hlen
     * @param key
     * @return
     */
    public long hlen(String key){
        return jedis.hlen(key);
    }

    /**
     * hincrby
     * @param key
     * @param field
     * @param increment
     * @return
     */
    public long hincrby(String key,String field,int increment){
        return jedis.hincrBy(key,field,increment);
    }

    /**
     * hkeys
     * @param key
     * @return
     */
    public Set<String> hkeys(String key){
        return jedis.hkeys(key);
    }



}
