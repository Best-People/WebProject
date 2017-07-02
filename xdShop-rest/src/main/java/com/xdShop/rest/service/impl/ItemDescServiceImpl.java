package com.xdShop.rest.service.impl;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.JsonUtils;
import com.xdShop.mapper.TbItemDescMapper;
import com.xdShop.pojo.TbItemDesc;
import com.xdShop.rest.dao.JedisClient;
import com.xdShop.rest.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by pro on 17/2/21.
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private TbItemDescMapper descMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    @Override
    public GoodsResult getItemDesc(long itemId) {
        //添加缓存逻辑
        //先去redis取 若无再去数据库中取
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            if (!StringUtils.isEmpty(json)) {
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return GoodsResult.ok(itemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //去数据库中取并加入缓存
        TbItemDesc itemDesc=descMapper.selectByPrimaryKey(itemId);
        if(itemDesc==null)
            return GoodsResult.build(500,"数据库中不存在在Id为"+itemId+"的商品描述信息!!!");
        try {
            //把商品信息加入缓存
            jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":desc",JsonUtils.objectToJson(itemDesc));
            //设置缓存的过期时间
            jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":desc",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return GoodsResult.ok(itemDesc);
    }
}
