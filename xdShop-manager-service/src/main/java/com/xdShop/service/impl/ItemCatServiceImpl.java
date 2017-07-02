package com.xdShop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdShop.common.pojo.EUTreeNode;
import com.xdShop.mapper.TbItemCatMapper;
import com.xdShop.pojo.TbItemCat;
import com.xdShop.pojo.TbItemCatExample;
import com.xdShop.pojo.TbItemCatExample.Criteria;
import com.xdShop.service.ItemCatService;
@Service

public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper mapper;
	
	
	@Override
	public List<EUTreeNode> getItemCatListByParentId(long parentId) {

		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list=mapper.selectByExample(example);
		//创建一个easyUI规定格式的list
		List<EUTreeNode> listTN=new ArrayList<EUTreeNode>();
		for(TbItemCat c:list){
			EUTreeNode node=new EUTreeNode(c.getId(),c.getName(),c.getIsParent()?"closed":"open");
			
			listTN.add(node);
		}
		if(listTN!=null&&listTN.size()>0)
			return listTN;
		return null;
	}
	public TbItemCatMapper getMapper() {
		return mapper;
	}
	public void setMapper(TbItemCatMapper mapper) {
		this.mapper = mapper;
	}

}
