package com.xdShop.order.service;

import java.util.List;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.pojo.TbOrder;
import com.xdShop.pojo.TbOrderItem;
import com.xdShop.pojo.TbOrderShipping;

public interface OrderService {

	GoodsResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
