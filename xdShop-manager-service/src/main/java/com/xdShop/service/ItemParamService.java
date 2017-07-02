package com.xdShop.service;


import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.pojo.TbItemParam;

public interface ItemParamService {
	public GoodsResult getItemParamById(int id);
	
	public GoodsResult addItemParam(TbItemParam itemParam);
}
