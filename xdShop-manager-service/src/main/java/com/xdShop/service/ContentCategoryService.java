package com.xdShop.service;

import java.util.List;

import com.xdShop.common.pojo.EUTreeNode;
import com.xdShop.common.pojo.GoodsResult;

public interface ContentCategoryService {
	List<EUTreeNode> getContCategoryListByParentId(long id);
	
	GoodsResult insert(long parentId,String name);
	
	GoodsResult deleteById(long id);

	void updateName(long id,String name);
}
