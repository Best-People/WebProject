package com.xdShop.rest.service;


import com.xdShop.common.pojo.GoodsResult;

/**
 * Created by pro on 17/2/20.
 */
public interface ItemService {
    GoodsResult getItemBaseInfo(long itemId);
}
