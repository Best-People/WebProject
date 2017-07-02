package com.xdShop.portal.service.impl;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.pojo.SearchResult;
import com.xdShop.common.utils.HttpClientUtil;
import com.xdShop.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 17/1/7.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, int page) {
        Map<String,String> params=new HashMap<String, String>();
        params.put("q",queryString);
        params.put("page",page+"");
        try{
            String json= HttpClientUtil.doGet(SEARCH_BASE_URL,params);
//            System.out.println(json);
//            String json1=new String(json.getBytes("ISO8859-1"),"UTF-8");
//            System.out.println(json1);
            GoodsResult result=GoodsResult.formatToPojo(json,SearchResult.class);
            if(result.getStatus()==200){
                SearchResult sr= (SearchResult) result.getData();
                return sr;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
