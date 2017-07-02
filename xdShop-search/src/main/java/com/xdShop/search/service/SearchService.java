package com.xdShop.search.service;

import com.xdShop.common.pojo.Item;
import com.xdShop.common.pojo.SearchResult;

/**
 * Created by pro on 17/1/3.
 */
public interface SearchService {
    SearchResult search(String queryStr, int page, int rows) throws Exception;


}
