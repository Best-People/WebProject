package com.xdShop.portal.controller;

import com.xdShop.portal.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@Autowired
	private AdService service;

	@RequestMapping("/index")
	public String showIndex(Model model){
		String json=service.getItemList();
//		System.out.println(json);
		model.addAttribute("ad1",json);
		return "index";
	}
}
