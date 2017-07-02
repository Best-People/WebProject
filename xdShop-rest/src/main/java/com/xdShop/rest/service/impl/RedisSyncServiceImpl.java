package com.xdShop.rest.service.impl;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.ExceptionUtil;
import com.xdShop.rest.dao.JedisClient;
import com.xdShop.rest.service.RedisSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pro on 16/11/30.
 */
@Service
public class RedisSyncServiceImpl implements RedisSyncService{

    @Autowired
    private JedisClient client;


    @Override
    public GoodsResult del(String key) {
        try {
            client.del(key);
        }catch (Exception e){
            e.printStackTrace();
            return GoodsResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return GoodsResult.ok();
    }

    @Override
    public GoodsResult hdel(String hkey, String key) {
        try {
            client.hdel(hkey,key);
        }catch (Exception e){
            e.printStackTrace();
            return GoodsResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return GoodsResult.ok();
    }
}
