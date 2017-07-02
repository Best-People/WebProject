package com.xdShop.rest.service.impl;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.JsonUtils;
import com.xdShop.mapper.TbItemMapper;
import com.xdShop.pojo.TbItem;
import com.xdShop.rest.dao.JedisClient;
import com.xdShop.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pro on 17/2/20.
 */
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    @Override
    public GoodsResult getItemBaseInfo(long itemId) {
        //添加缓存逻辑
        //先去redis取 若无再去数据库中取
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            if (!StringUtils.isEmpty(json)) {
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                return GoodsResult.ok(item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //去数据库中取并加入缓存
        TbItem item=itemMapper.selectByPrimaryKey(itemId);
        try {
            //把商品信息加入缓存
            jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":base",JsonUtils.objectToJson(item));
            //设置缓存的过期时间
            jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":base",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }

        return GoodsResult.ok(item);
    }
}
