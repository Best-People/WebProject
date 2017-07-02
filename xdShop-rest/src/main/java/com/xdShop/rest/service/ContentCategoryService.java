package com.xdShop.rest.service;

import java.util.List;

import com.xdShop.common.pojo.EUTreeNode;

public interface ContentCategoryService {
	List<EUTreeNode> getContCategoryListByParentId(long id);
}
