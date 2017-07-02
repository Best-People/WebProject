package com.xdShop.rest.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdShop.common.utils.JsonUtils;
import com.xdShop.rest.pojo.CategoryResult;
import com.xdShop.rest.service.CategoryService;

@Controller
@RequestMapping("/rest/itemcat")
public class ItemCatController {
	@Autowired
	private CategoryService service;
	@RequestMapping(value="/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getCategory(String callback,HttpServletResponse response){
		CategoryResult cr=service.getGategory();
		String json=JsonUtils.objectToJson(cr);
		String s=callback+"("+json+");";
//		System.out.println(s);
		return s;
	}
}
