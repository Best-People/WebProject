package com.xdShop.service;

import com.xdShop.common.pojo.EUItemListResult;
import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.pojo.TbContent;

/**
 * Created by pro on 16/11/12.
 */
public interface ContentService {
    EUItemListResult queryList(long categoryId,int page,int rows);

    GoodsResult updateContent(TbContent tc);

    GoodsResult deleteByids(long[] ids);

    GoodsResult save(TbContent tc);
}
