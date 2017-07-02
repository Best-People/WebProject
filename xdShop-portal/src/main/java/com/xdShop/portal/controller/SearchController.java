package com.xdShop.portal.controller;

import com.xdShop.common.pojo.SearchResult;
import com.xdShop.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * Created by pro on 17/1/7.
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;


    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryStr,
                         @RequestParam(defaultValue = "1")int page, Model model){
        if(queryStr!=null){
            try{
                queryStr=new String(queryStr.getBytes("ISO8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        SearchResult result=searchService.search(queryStr,page);
        //向页面传递参数
        //查询条件
        model.addAttribute("query",queryStr);
        //总页数
        model.addAttribute("totalPages",result.getPageCount());
        //查询到的商品集
        model.addAttribute("itemList",result.getItemList());
        //当前是第几页
        model.addAttribute("page",result.getCurPage());

//        System.out.println("调用了search");
        System.out.println(result.getItemList().get(0).getTitle());
        return "search";
    }

}
