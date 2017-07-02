package com.xdShop.service;

import java.util.List;

import com.xdShop.common.pojo.EUTreeNode;
import com.xdShop.pojo.TbItemCat;

public interface ItemCatService {
	public List<EUTreeNode> getItemCatListByParentId(long id);
}