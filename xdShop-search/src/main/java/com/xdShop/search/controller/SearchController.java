package com.xdShop.search.controller;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.pojo.SearchResult;
import com.xdShop.common.utils.ExceptionUtil;
import com.xdShop.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * Created by pro on 17/1/3.
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService service;

    @RequestMapping(value="/query",method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public GoodsResult search(@RequestParam("q") String queryStr ,
                              @RequestParam(defaultValue ="1") Integer page,
                              @RequestParam(defaultValue ="60") Integer rows){
        try {
            queryStr=new String(queryStr.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(queryStr==null||queryStr.trim().isEmpty()){
            return GoodsResult.build(400,"查询条件不能为空");
        }
//        System.out.println("调用了query");
//        System.out.println(queryStr);
        SearchResult result=null;
        try {
            result=service.search(queryStr,page,rows);
//            System.out.println(result.getItemList().get(0).getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            return GoodsResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return GoodsResult.ok(result);
    }
}
