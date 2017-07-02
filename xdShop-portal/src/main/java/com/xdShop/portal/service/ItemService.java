package com.xdShop.portal.service;

import com.xdShop.pojo.TbItemDesc;
import com.xdShop.pojo.TbItemParamItem;
import com.xdShop.portal.POJO.ItemInfo;

/**
 * Created by pro on 17/2/21.
 */
public interface ItemService {
    ItemInfo getItemById(long itemId);

    String getItemDesc(long itemId);

    String getItemParam(long itemId);

}
