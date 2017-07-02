package com.xdShop.portal.service;

import com.xdShop.common.pojo.SearchResult;

/**
 * Created by pro on 17/1/7.
 */
public interface SearchService {
    SearchResult search(String queryString ,int page);
}
