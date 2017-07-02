package com.xdShop.rest.dao.impl;

import com.xdShop.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * Created by pro on 16/11/30.
 */
public class JedisClientCluster implements JedisClient {

    @Autowired
    private JedisCluster cluster;

    @Override
    public String get(String key) {
        return cluster.get(key);
    }


    @Override
    public String set(String key, String value) {
        return cluster.set(key,value);
    }

    @Override
    public String hget(String hkey, String key) {
        return cluster.hget(hkey,key);
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        return cluster.hset(hkey,key,value);
    }

    @Override
    public long incr(String key) {
        return cluster.incr(key);
    }

    @Override
    public long expire(String key, int seconds) {
        return cluster.expire(key,seconds);
    }

    @Override
    public long ttl(String key) {
        return cluster.ttl(key);
    }

    @Override
    public long del(String key) {
        return cluster.del(key);
    }

    @Override
    public long hdel(String hkey, String key) {
        return cluster.hdel(hkey,key);
    }
}
