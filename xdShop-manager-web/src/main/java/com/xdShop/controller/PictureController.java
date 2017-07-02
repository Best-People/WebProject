package com.xdShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xdShop.common.pojo.PictureResult;
import com.xdShop.common.utils.JsonUtils;
import com.xdShop.service.PictureService;

@Controller
public class PictureController {
	@Autowired
	private PictureService service;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String  upLoadPicture(MultipartFile uploadFile){
		PictureResult result = service.upLoadPicture(uploadFile);
		//保证在各个浏览器的兼容性
		String json=JsonUtils.objectToJson(result);

		//将对象转化成json格式的数据
		return json;
	}

}
