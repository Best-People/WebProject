package com.xdShop.rest.controller;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.rest.service.ItemDescService;
import com.xdShop.rest.service.ItemParamService;
import com.xdShop.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pro on 17/2/20.
 */
@Controller
@RequestMapping("/rest/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public GoodsResult getItemBaseInfo(@PathVariable Long itemId){
        return itemService.getItemBaseInfo(itemId);
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public GoodsResult getItemDesc(@PathVariable Long itemId){
        return itemDescService.getItemDesc(itemId);
    }

    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public GoodsResult getItemParam(@PathVariable Long itemId){
        return itemParamService.getItemParams(itemId);
    }
}