package com.xdShop.rest.service.impl;

import com.xdShop.common.utils.JsonUtils;
import com.xdShop.mapper.TbContentMapper;
import com.xdShop.pojo.TbContent;
import com.xdShop.pojo.TbContentExample;
import com.xdShop.rest.dao.JedisClient;
import com.xdShop.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 16/11/12.
 */
@Service
public class ContentServiceImpl  implements ContentService{
    @Autowired
    private TbContentMapper mapper;

    @Autowired
    private JedisClient jedis;

    @Value("${INDEX_CONTENT_REDIS_HKEY}")
    private String INDEX_CONTENT_REDIS_HKEY;

    @Override
    public List<TbContent> getContenList(long categoryId) {
        //从redis缓存中取
        String result=null;
        try {
            result=jedis.hget(INDEX_CONTENT_REDIS_HKEY,String.valueOf(categoryId));
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result!=null&&!result.isEmpty()){
            List<TbContent> list=new ArrayList<TbContent>();
            list= JsonUtils.jsonToList(result,TbContent.class);
            return list;
        }


        List<TbContent> list=new ArrayList<TbContent>();
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria c=example.createCriteria();
        c.andCategoryIdEqualTo(categoryId);
        list=mapper.selectByExample(example);

        //将结果添加到redis缓存中
        try{
            result=JsonUtils.objectToJson(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        jedis.hset(INDEX_CONTENT_REDIS_HKEY,String.valueOf(categoryId),result);
        System.out.println("没有从缓存中取数据,现在将数据加入缓存中!!!");
        return list;
    }
}
