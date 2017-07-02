package com.xdShop.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.pojo.TbItemParam;
import com.xdShop.service.ItemParamItemService;
import com.xdShop.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService service;
	@Autowired
	private ItemParamItemService ipiService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public GoodsResult getTbItemParam(@PathVariable int itemCatId){
		return service.getItemParamById(itemCatId);
	}
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public GoodsResult addTbItemParam(TbItemParam paramdate,@PathVariable int cid){
		paramdate.setItemCatId((long)cid);
		Date date=new Date();
		paramdate.setCreated(date);
		paramdate.setUpdated(date);
		return service.addItemParam(paramdate);
	}
	//获取规格值
	@RequestMapping("/showInfo/{itemId}")
	public String getItemParamInfoById(@PathVariable int itemId,Model m){
		String html=ipiService.getItemParamInfoById(itemId);
		m.addAttribute("paramInfo", html);
		return "paramInfo";
	}

}
