package com.xdShop.controller;

import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class TestFTP {
	@Test
	public void test() throws Exception{
		//创建ftp客户端
		FTPClient client=new FTPClient();
		//ftp连接的地址和端口
		client.connect("192.168.1.104",21);
		//ftp用户和密码登陆
		client.login("ftpuser", "895821");
		//创建一个io读取本地文件
		FileInputStream stream=new FileInputStream("F:\\our picture\\IMG_20160420_162153.jpg");
		//1）指定上传目录
		client.changeWorkingDirectory("/home/ftpuse/");
		//2）指定文件类型
		client.setFileType(FTPClient.BINARY_FILE_TYPE);
		//ftp上传文件
		client.storeFile("hello.jpg", stream);
		//ftp登出
		client.logout();
	}
}
