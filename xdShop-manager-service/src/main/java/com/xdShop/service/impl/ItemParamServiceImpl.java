package com.xdShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.mapper.TbItemParamMapper;
import com.xdShop.pojo.TbItemParam;
import com.xdShop.pojo.TbItemParamExample;
import com.xdShop.pojo.TbItemParamExample.Criteria;
import com.xdShop.service.ItemParamService;
@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Autowired
	private TbItemParamMapper mapper;
	
	@Override
	public GoodsResult getItemParamById(int id) {
		TbItemParamExample example=new TbItemParamExample();
		Criteria c=example.createCriteria();
		c.andItemCatIdEqualTo((long)id);
		List<TbItemParam> list=mapper.selectByExampleWithBLOBs(example);
		if(list==null||list.size()<=0)
			return GoodsResult.ok();
		return GoodsResult.ok(list.get(0));
	}

	@Override
	public GoodsResult addItemParam(TbItemParam itemParam) {
		mapper.insert(itemParam);
		return GoodsResult.ok();
	}

}
