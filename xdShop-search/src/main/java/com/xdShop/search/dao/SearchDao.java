package com.xdShop.search.dao;

import com.xdShop.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by pro on 17/1/3.
 */
public interface SearchDao {


    SearchResult search(SolrQuery query) throws Exception;
}
