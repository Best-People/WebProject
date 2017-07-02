package com.xdShop.rest.service;

import com.xdShop.common.pojo.GoodsResult;

/**
 * Created by pro on 16/11/30.
 */
public interface RedisSyncService {

    GoodsResult del(String key);

    GoodsResult hdel(String hkey,String key);
}
