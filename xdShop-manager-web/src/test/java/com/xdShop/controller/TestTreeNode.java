package com.xdShop.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xdShop.mapper.TbItemCatMapper;
import com.xdShop.pojo.TbItemCat;
import com.xdShop.pojo.TbItemCatExample;
import com.xdShop.pojo.TbItemCatExample.Criteria;

public class TestTreeNode {
	
	@Test
	public void test(){
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml"); 
		TbItemCatMapper mapper=context.getBean(TbItemCatMapper.class);
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(0l);
		List<TbItemCat> list=mapper.selectByExample(example);
		
		for(TbItemCat t:list){
			System.out.println(t.getName());
		}
				
	}
}
