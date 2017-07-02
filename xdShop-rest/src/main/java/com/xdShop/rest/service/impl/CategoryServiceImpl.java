package com.xdShop.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdShop.mapper.TbItemCatMapper;
import com.xdShop.pojo.TbItemCat;
import com.xdShop.pojo.TbItemCatExample;
import com.xdShop.pojo.TbItemCatExample.Criteria;
import com.xdShop.rest.pojo.CatNode;
import com.xdShop.rest.pojo.CategoryResult;
import com.xdShop.rest.service.CategoryService;
@Service

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private TbItemCatMapper mapper;
	@Override
	public CategoryResult getGategory() {
		CategoryResult cr=new CategoryResult();
		cr.setData(getListByParentId(0));
		return cr;
	}
	
	private List<?> getListByParentId(long pid){
		TbItemCatExample example=new TbItemCatExample();
		Criteria c=example.createCriteria();
		c.andParentIdEqualTo(pid);
		List<TbItemCat> list=mapper.selectByExample(example);
		List results=new ArrayList();
		for(TbItemCat cat:list){
			String url="/products/"+cat.getId()+".html";
			//如果是父节点
			if(cat.getIsParent()){
				CatNode node=new CatNode();
				node.setU(url);
				String name;
				if(cat.getParentId()==0)
					name="<a href='"
						+url+"'>"+cat.getName()+"</a>";
				else
					name=cat.getName();
				node.setN(name);
				node.setI(getListByParentId(cat.getId()));
				results.add(node);
			}
			else
				results.add(url+"|"+cat.getName());
		}
		return results;
	}
}
