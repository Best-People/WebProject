package com.xdShop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdShop.common.pojo.EUTreeNode;
import com.xdShop.rest.service.ContentCategoryService;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService service;
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EUTreeNode> getListById(@RequestParam(value="id",defaultValue="0")long parentId){
		List<EUTreeNode> list =
				service.getContCategoryListByParentId(parentId);

		return list;
	}
}
