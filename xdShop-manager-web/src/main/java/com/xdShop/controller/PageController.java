package com.xdShop.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//控制页面跳转的controller
@Controller
public class PageController {
	
	//打开后台首页
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	//跳转到其他页面
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
