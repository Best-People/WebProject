package com.xdShop.search.dao.impl;

import com.xdShop.common.pojo.Item;
import com.xdShop.common.pojo.SearchResult;
import com.xdShop.search.dao.SearchDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17/1/3.
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {

        SearchResult result=new SearchResult();
        QueryResponse response=solrServer.query(query);;
        //根据查询条件查询索引库r
        //获取查询结果
        SolrDocumentList documentList=response.getResults();
        //设置查询结果数
        result.setRecordCount(documentList.getNumFound());
        //将查询到的信息装入result
        List<Item> lists=new ArrayList<Item>();
        //取高亮显示
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        for(SolrDocument document:documentList){
            Item item = new Item();
            item.setId((String) document.get("id"));
            //取高亮显示的结果
            List<String> list = highlighting.get(document.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size()>0) {
                title = list.get(0);
            } else {
                title = (String) document.get("item_title");
            }
            item.setTitle(title);
            item.setImage((String) document.get("item_image"));
            item.setPrice((long) document.get("item_price"));
            item.setSell_point((String) document.get("item_sell_point"));
            item.setCategory_name((String) document.get("item_category_name"));
            //添加的商品列表
            lists.add(item);

        }
        result.setItemList(lists);
        //
        return result;
    }
}
