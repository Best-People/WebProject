package com.xdShop.portal.controller;

import com.xdShop.portal.POJO.ItemInfo;
import com.xdShop.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pro on 17/2/21.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable long itemId, Model model){
        ItemInfo item=itemService.getItemById(itemId);
        model.addAttribute("item",item);
        return "item";
    }



    @RequestMapping(value="/item/desc/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemDesc(@PathVariable long itemId){
        String  itemDesc=itemService.getItemDesc(itemId);
        return itemDesc;
    }



    @RequestMapping(value="/item/param/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemParam(@PathVariable long itemId){
        String paramItem=itemService.getItemParam(itemId);
        System.out.println(paramItem);
        return paramItem;
    }


}
