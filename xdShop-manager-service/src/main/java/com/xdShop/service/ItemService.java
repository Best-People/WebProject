package com.xdShop.service;



import com.xdShop.common.pojo.EUItemListResult;
import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.pojo.TbItem;

public interface ItemService {
	
	TbItem getItemById(long itemId);
	
	EUItemListResult getTbItemList(int page,int rows);

	GoodsResult addItem(TbItem item, String desc, String itemParams);
	
}
