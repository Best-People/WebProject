package com.xdShop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdShop.common.pojo.EUTreeNode;
import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.service.ContentCategoryService;


@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService service;

	@RequestMapping("/list")
	@ResponseBody
        public List<EUTreeNode> getListById(@RequestParam(value="id",defaultValue="0")long parentId){
		List<EUTreeNode> list=
				service.getContCategoryListByParentId(parentId);
		return list;
	}


	@RequestMapping("/create")
	@ResponseBody
	public GoodsResult insertContentCategory(long parentId,String name){
		return service.insert(parentId, name);
	}

	@RequestMapping("/delete")
	public GoodsResult deleteById(long id){
		System.out.println("调用了Controller");
		return service.deleteById(id);
	}

	@RequestMapping("/update")
	public void updateName(long id,String name){
		service.updateName(id,name);
	}

}

