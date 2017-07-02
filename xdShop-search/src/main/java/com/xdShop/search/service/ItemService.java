package com.xdShop.search.service;

import com.xdShop.common.pojo.GoodsResult;
import org.springframework.stereotype.Service;

/**
 * Created by pro on 17/1/3.
 */
public interface ItemService {

    GoodsResult importAllItems();

    GoodsResult importOne();
}
