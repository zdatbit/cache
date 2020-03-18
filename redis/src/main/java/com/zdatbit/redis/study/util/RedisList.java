package com.zdatbit.redis.study.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.util.List;

public class RedisList {

    private Jedis jedis;

    public RedisList(){
        jedis = RedisUtil.getJedisFromPool();
    }

    /**
     * lpush
     * @param key
     * @param values
     */
    public void lpush(String key,String... values){
        jedis.lpush(key,values);
    }

    /**
     * lpop
     * @param key
     * @return
     */
    public String lpop(String key){
        return jedis.lpop(key);
    }


    /**
     * lpushx
     * @param key
     * @param value
     */
    public void lpushx(String key,String value){
        jedis.lpushx(key,value);
    }

    /**
     * rpush
     * @param key
     * @param values
     */
    public void rpush(String key,String... values){
        jedis.rpush(key,values);
    }

    /**
     * rpop
     * @param key
     * @return
     */
    public String rpop(String key){
        return jedis.rpop(key);
    }

    /**
     * rpushx
     * @param key
     * @param value
     */
    public void rpushx(String key,String value){
        jedis.rpushx(key,value);
    }

    /**
     * llen
     * @param key
     * @return
     */
    public long llen(String key){
        return jedis.llen(key);
    }

    /**
     * rpoplpush
     * @param source
     * @param distination
     * @return
     */
    public String rpoprpush(String source,String distination){
        return jedis.rpoplpush(source,distination);
    }

    /**
     * lset
     * @param key
     * @param index
     * @param value
     */
    public void lset(String key,int index,String value){
        jedis.lset(key,index,value);
    }

    /**
     * lindex
     * @param key
     * @param index
     * @return
     */
    public String lindex(String key,int index){
        return jedis.lindex(key,index);
    }

    /**
     * linsert
     * @param key
     * @param pivot
     * @param value
     */
    public void linsertAfter(String key,String pivot,String value){
        jedis.linsert(key, ListPosition.AFTER,pivot,value);
    }

    /**
     * linsert
     * @param key
     * @param pivot
     * @param value
     */
    public void linsertBefore(String key,String pivot,String value){
        jedis.linsert(key,ListPosition.AFTER.BEFORE,pivot,value);
    }

    /**
     * lrange
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key, int start, int end){
        return jedis.lrange(key,start,end);
    }

    /**
     * ltrim
     * @param key
     * @param start
     * @param end
     */
    public void ltrim(String key,int start,int end){
        jedis.ltrim(key,start,end);
    }


}
