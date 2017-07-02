package com.xdShop.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.xdShop.common.pojo.PictureResult;
import com.xdShop.service.impl.PictrueServiceImpl;

public interface PictureService {
	
	public  PictureResult upLoadPicture(MultipartFile upLoadFile);
	
	
}
