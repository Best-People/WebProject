package com.xdShop.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xdShop.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;

import com.xdShop.common.pojo.EUTreeNode;
import com.xdShop.mapper.TbContentCategoryMapper;
import com.xdShop.pojo.TbContentCategory;
import com.xdShop.pojo.TbContentCategoryExample;
import com.xdShop.pojo.TbContentCategoryExample.Criteria;
import com.xdShop.rest.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper mapper;

	@Autowired
	private JedisClient jedis;

	@Override
	public List<EUTreeNode> getContCategoryListByParentId(long parentId) {

//		String result=jedis.hget();

		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria c=example.createCriteria();
		c.andIdEqualTo(parentId);
		List<TbContentCategory> list=mapper.selectByExample(example);
		List<EUTreeNode> trees=new ArrayList<EUTreeNode>();
		for(TbContentCategory tcc:list)
			trees.add(new EUTreeNode((long)tcc.getId(),tcc.getName(),tcc.getIsParent()?"closed":"open"));
		if(trees==null||trees.size()<=0)
			return null;

		return trees;
	}

}
