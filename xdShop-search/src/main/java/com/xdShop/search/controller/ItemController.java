package com.xdShop.search.controller;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pro on 17/1/3.
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    private ItemService service;

    @RequestMapping("/importAll")
    @ResponseBody
    public GoodsResult importAllItems(){
        return service.importAllItems();

    }

//    @RequestMapping("/importOne")
//    public GoodsResult importOne(){
//
//    }
}
