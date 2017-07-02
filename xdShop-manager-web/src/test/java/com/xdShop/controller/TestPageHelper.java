package com.xdShop.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdShop.mapper.TbItemMapper;
import com.xdShop.pojo.TbItem;
import com.xdShop.pojo.TbItemExample;

public class TestPageHelper {
	public static  ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath:spring/applicationContext-*.xml"); 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// 加载spring容器
				
	}

	@Test
	public void testPageHelper() {
		
		// 获取商品的mapper类
		TbItemMapper itemMapper = context.getBean(TbItemMapper.class);
		// 分页查询
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(1, 10);
		List<TbItem> list = new ArrayList<TbItem>();
		list = itemMapper.selectByExample(example);
		// 数据显示
		for (TbItem item : list) {
			System.out.println(item.getTitle());
		}
		PageInfo<TbItem> info = new PageInfo<TbItem>(list);
		System.out.println("共有商品:" + info.getTotal());
	}
}
