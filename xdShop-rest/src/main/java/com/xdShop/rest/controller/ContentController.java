package com.xdShop.rest.controller;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.ExceptionUtil;
import com.xdShop.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * Created by pro on 16/11/12.
 */
@Controller
@RequestMapping("/rest/content")
public class ContentController {
    @Autowired
    private ContentService service;

    @RequestMapping("/list/{categoryId}")
    @ResponseBody
    public GoodsResult getList(@PathVariable Long categoryId){
        GoodsResult result;
        try {
            result=new GoodsResult(service.getContenList(categoryId));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return GoodsResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}


