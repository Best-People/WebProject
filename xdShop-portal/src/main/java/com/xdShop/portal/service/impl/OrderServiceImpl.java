package com.xdShop.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.HttpClientUtil;
import com.xdShop.common.utils.JsonUtils;
import com.xdShop.portal.POJO.Order;
import com.xdShop.portal.service.OrderService;

/**
 * 订单处理Service

 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	

	@Override
	public String createOrder(Order order) {
		//调用创建订单服务之前补全用户信息。
		//从cookie中后取TT_TOKEN的内容，根据token调用sso系统的服务根据token换取用户信息。
		
		//调用xdShop-order的服务提交订单。
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		//把json转换成xdShopResult
		GoodsResult xdShopResult = GoodsResult.format(json);
		if (xdShopResult.getStatus() == 200) {
			Object orderId = xdShopResult.getData();
			return orderId.toString();
		}
		return "";
	}

}