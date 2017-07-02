package com.xdShop.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;











import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdShop.common.pojo.EUItemListResult;
import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.IDUtils;
import com.xdShop.mapper.TbItemDescMapper;
import com.xdShop.mapper.TbItemMapper;
import com.xdShop.mapper.TbItemParamItemMapper;
import com.xdShop.mapper.TbItemParamMapper;
import com.xdShop.pojo.TbItem;
import com.xdShop.pojo.TbItemDesc;
import com.xdShop.pojo.TbItemExample;
import com.xdShop.pojo.TbItemExample.Criteria;
import com.xdShop.pojo.TbItemParamItem;
import com.xdShop.service.ItemService;
/*
 * 商品的service组件
 */



@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper paramItemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		
		TbItemExample example=new TbItemExample();
		Criteria cri=example.createCriteria();
		cri.andIdEqualTo(itemId);
		List<TbItem> list=(List<TbItem>) itemMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			TbItem t=list.get(0);
			return t;
		}
		return null;
	}
	@Override
	public EUItemListResult getTbItemList(int page, int rows) {
		
		TbItemExample example=new TbItemExample();
		//分页
		PageHelper.startPage(page,rows);
		
		List<TbItem> list=itemMapper.selectByExample(example);
		
		PageInfo<TbItem> info = new PageInfo<TbItem>(list);
		
		EUItemListResult result=new EUItemListResult((int)info.getTotal(),list);
		
		return result;
	}
	@Override
	public GoodsResult addItem(TbItem item,String desc, String itemParams) {
		//生成商品id
		long id=IDUtils.genItemId();
		item.setId(id);
		
		//商品状态,1:正常,2:下架,3:删除
		item.setStatus((byte)1);
		//商品创建时间
		Date date=new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//向数据库插入商品信息
		itemMapper.insert(item);
		
		//商品描述信息 它与商品分别保存在两张不同的表中
		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDescMapper.insert(itemDesc);
		
		//保存商品规格信息
		TbItemParamItem paramItem=new TbItemParamItem();
		paramItem.setItemId(id);
		paramItem.setParamData(itemParams);
		paramItem.setCreated(date);
		paramItem.setUpdated(date);
		paramItemMapper.insert(paramItem);
		
		return GoodsResult.ok();
	}


}
