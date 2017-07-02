package com.xdShop.rest.controller;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.rest.service.RedisSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pro on 16/11/30.
 */
@Controller
@RequestMapping("/cache/syn/")
public class RedisCacheController {

    @Autowired
    private RedisSyncService service;

    @Value("${INDEX_CONTENT_REDIS_HKEY}")
    private String INDEX_CONTENT_REDIS_HKEY;


    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public GoodsResult syn(@PathVariable long contentCid){
        return  service.hdel(INDEX_CONTENT_REDIS_HKEY,String.valueOf(contentCid));
    }

}
