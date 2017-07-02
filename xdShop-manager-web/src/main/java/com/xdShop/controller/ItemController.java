package com.xdShop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdShop.common.pojo.EUItemListResult;
import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.pojo.TbItem;
import com.xdShop.service.ItemService;


@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem item=itemService.getItemById(itemId);
		return item;
	}
	@RequestMapping("/list")
	@ResponseBody
	public EUItemListResult getItemList(int page,int rows){
		EUItemListResult result=itemService.getTbItemList(page, rows);
		return result;
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public GoodsResult addItem(TbItem item,String desc,String itemParams){
		return itemService.addItem(item, desc, itemParams);
	}
	
}
