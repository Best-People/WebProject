package com.xdShop.search.service.impl;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.pojo.Item;
import com.xdShop.common.utils.ExceptionUtil;
import com.xdShop.search.mapper.ItemMapper;
import com.xdShop.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pro on 17/1/3.
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private ItemMapper mapper;

    @Override
    public GoodsResult importAllItems() {
        List<Item> lists=mapper.getItemList();
        for(int i=0;i< lists.size();++i){
            Item item= lists.get(i);
            SolrInputDocument document=new SolrInputDocument();
            document.setField("id",item.getId());
            document.setField("item_title",item.getTitle());
            document.setField("item_sell_point",item.getSell_point());
            document.setField("item_price",item.getPrice());
            document.setField("item_image",item.getImage());
            document.setField("item_category_name",item.getCategory_name());
            document.setField("item_desc",item.getItem_desc());
            try {
                solrServer.add(document);
                if(i>=lists.size()-1)
                    solrServer.commit();
            } catch(Exception e){
                e.printStackTrace();
                return GoodsResult.build(500, ExceptionUtil.getStackTrace(e));
            }
        }
        return GoodsResult.ok();
    }

    @Override
    public GoodsResult importOne() {
        return null;
    }

}
