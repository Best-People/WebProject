package com.xdShop.portal.service.impl;

import com.xdShop.common.pojo.ADItem;
import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.HttpClientUtil;
import com.xdShop.common.utils.JsonUtils;
import com.xdShop.pojo.TbContent;
import com.xdShop.portal.service.AdService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 16/11/17.
 */
@Service
public class AdServiceImpl implements AdService{
    @Value("${REST_BASE_PATH}")
    private String REST_BASE_PATH;
    @Value("${INDEX_AD1_URL}")
    private String INDEX_AD1_URI;

    @Override
    public String getItemList() {
        //post请求返回了一个GoodsResult对象的json字符串
        String   originDate =  HttpClientUtil.doPost(REST_BASE_PATH+INDEX_AD1_URI);
        GoodsResult gr=JsonUtils.jsonToPojo(originDate, GoodsResult.class);
        String tcListJson =JsonUtils.objectToJson(gr.getData());
        //这个data包装了指定categoryId下的所有Content
        if(gr==null)
            return null;
//        List<TbContent> list= (List<TbContent>) gr.getData();
        List<TbContent> list=JsonUtils.jsonToList(tcListJson,TbContent.class);
        List<ADItem> adItems=new ArrayList<ADItem>();
        for(TbContent tc:list){
            ADItem item=new ADItem();
            item.setHeight(240);
            item.setWdith(670);
            item.setSrc(tc.getPic());
            item.setHeitghB(240);
            item.setWidthB(550);
            item.setSrcB(tc.getPic2());
            item.setHref(tc.getUrl());
            item.setAlt(tc.getTitleDesc());
            adItems.add(item);
        }
        return JsonUtils.objectToJson(adItems);
    }
}
