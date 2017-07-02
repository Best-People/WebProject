package com.xdShop.service.impl;

import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xdShop.common.utils.FtpUtil;
import com.xdShop.common.utils.IDUtils;
import com.xdShop.common.pojo.PictureResult;
import com.xdShop.service.PictureService;

@Service
public class PictrueServiceImpl implements PictureService {
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private int FTP_PORT;
	@Value("${FTP_USER_NAME}")
	private String FTP_USER_NAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_URL}")
	private String IMAGE_BASE_URL;
	@Override
	public PictureResult upLoadPicture(MultipartFile upLoadFile) {
		//判断上传的图片是否为空
		if(upLoadFile==null){
			//url为null,error为1
			return new PictureResult(null,"图片不能为空",1);
		}
		//获得文件名
		String originFileName=upLoadFile.getOriginalFilename();
		//文件扩展名
		String ext=originFileName.substring(originFileName.lastIndexOf("."));
		//生成新的文件名
		String newFileName=IDUtils.genImageName();
		newFileName+=ext;
		//文件路径
		DateTime dateTime = new DateTime();
		String filePath = dateTime.toString("/yyyy/MM/dd");
		//上传文件
		try{
			FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USER_NAME, 
					FTP_PASSWORD,FTP_BASE_PATH,filePath,
					newFileName, upLoadFile.getInputStream());
			
		}catch(Exception e){
			e.printStackTrace();
			return new PictureResult(null,"文件上传失败",1);
		}
		//
		return new PictureResult(IMAGE_BASE_URL+filePath+"/"+newFileName,null,0);
	}
	public String getFTP_ADDRESS() {
		return FTP_ADDRESS;
	}
	public void setFTP_ADDRESS(String fTP_ADDRESS) {
		FTP_ADDRESS = fTP_ADDRESS;
	}
	public int getFTP_PORT() {
		return FTP_PORT;
	}
	public void setFTP_PORT(int fTP_PORT) {
		FTP_PORT = fTP_PORT;
	}
	public String getFTP_USER_NAME() {
		return FTP_USER_NAME;
	}
	public void setFTP_USER_NAME(String fTP_USER_NAME) {
		FTP_USER_NAME = fTP_USER_NAME;
	}
	public String getFTP_PASSWORD() {
		return FTP_PASSWORD;
	}
	public void setFTP_PASSWORD(String fTP_PASSWORD) {
		FTP_PASSWORD = fTP_PASSWORD;
	}
	public String getFTP_BASE_PATH() {
		return FTP_BASE_PATH;
	}
	public void setFTP_BASE_PATH(String fTP_BASE_PATH) {
		FTP_BASE_PATH = fTP_BASE_PATH;
	}
	public String getIMAGE_URL() {
		return IMAGE_BASE_URL;
	}
	public void setIMAGE_URL(String iMAGE_URL) {
		IMAGE_BASE_URL = iMAGE_URL;
	}
	
}
