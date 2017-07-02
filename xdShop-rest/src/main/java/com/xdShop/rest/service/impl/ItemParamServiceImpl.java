package com.xdShop.rest.service.impl;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.JsonUtils;
import com.xdShop.mapper.TbItemParamItemMapper;
import com.xdShop.pojo.TbItemParamItem;
import com.xdShop.pojo.TbItemParamItem;
import com.xdShop.pojo.TbItemParamItemExample;
import com.xdShop.rest.dao.JedisClient;
import com.xdShop.rest.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by pro on 17/2/21.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamItemMapper paramItemMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    @Override
    public GoodsResult getItemParams(long itemId) {
        //添加缓存逻辑
        //先去redis取 若无再去数据库中取
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            if (!StringUtils.isEmpty(json)) {
                TbItemParamItem paramItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return GoodsResult.ok(paramItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //去数据库中取并加入缓存
        TbItemParamItem paramItem;
        TbItemParamItemExample example=new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria=example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list=paramItemMapper.selectByExampleWithBLOBs(example);
        if(list==null||list.size()<=0)
            return GoodsResult.build(500,"数据库中不存在在Id为"+itemId+"的商品规格参数信息!!!");
        else
            paramItem=list.get(0);
        try {
            //把商品信息加入缓存
            jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":param",JsonUtils.objectToJson(paramItem));
            //设置缓存的过期时间
            jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":param",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("rest:"+paramItem.getParamData());
        return GoodsResult.ok(paramItem);
    }
}
