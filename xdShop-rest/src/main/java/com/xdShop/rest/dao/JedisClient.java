package com.xdShop.rest.dao;

/**
 * Created by pro on 16/11/30.
 */
public interface JedisClient {

    String get(String key);

    String set(String key,String value);

    String hget(String hkey,String key);

    Long hset(String hkey, String key, String value);

    long incr(String key);

    long expire(String key, int seconds);

    long ttl(String key);

    long del(String key);

    long hdel(String hkey,String key);

}
