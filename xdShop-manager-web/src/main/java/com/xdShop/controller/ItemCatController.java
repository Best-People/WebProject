package com.xdShop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdShop.common.pojo.EUTreeNode;
import com.xdShop.pojo.TbItemCat;
import com.xdShop.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService service;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> list(@RequestParam(value="id", defaultValue="0")Long parentId){
		//调用service获取指定id类目下的子类目
		List<EUTreeNode> list=service.getItemCatListByParentId(parentId);

		return list;

	}
	
	public ItemCatService getService() {
		return service;
	}

	public void setService(ItemCatService service) {
		this.service = service;
	}
}
