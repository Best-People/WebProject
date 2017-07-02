package com.xdShop.controller;

import com.xdShop.common.pojo.EUItemListResult;
import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.pojo.TbContent;
import com.xdShop.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pro on 16/11/12.
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService service;

    @RequestMapping("/query/list")
    @ResponseBody
    public EUItemListResult list(long categoryId,int page,int rows){
        return service.queryList(categoryId,page,rows);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public GoodsResult edit(TbContent tc){
        return service.updateContent(tc);
    }

    @RequestMapping("/save")
    @ResponseBody
    public GoodsResult save(TbContent tc){
        return service.save(tc);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public GoodsResult deleteByIds(long[] ids){
        return service.deleteByids(ids);

    }
}
