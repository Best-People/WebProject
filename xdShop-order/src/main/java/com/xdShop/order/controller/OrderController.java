package com.xdShop.order.controller;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.ExceptionUtil;
import com.xdShop.order.pojo.Order;
import com.xdShop.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 订单Controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public GoodsResult createOrder(@RequestBody Order order) {
		System.out.println("jhhhh~");
		try {
			GoodsResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return GoodsResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	@RequestMapping("/test")
	public void test(){
		System.out.println("ok!!");
		return ;
	}
}
