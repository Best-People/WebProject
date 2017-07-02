package com.xdShop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdShop.common.pojo.EUItemListResult;
import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.mapper.TbContentMapper;
import com.xdShop.pojo.TbContent;
import com.xdShop.pojo.TbContentExample;
import com.xdShop.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pro on 16/11/12.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper mapper;


    @Override
    public EUItemListResult queryList(long categoryId, int page, int rows) {
        TbContentExample example= new TbContentExample();
        TbContentExample.Criteria c=example.createCriteria();
        c.andCategoryIdEqualTo(categoryId);
        // /分页
        PageHelper.startPage(page,rows);

        List<TbContent> list=mapper.selectByExample(example);

        PageInfo<TbContent> info = new PageInfo<TbContent>(list);

        EUItemListResult result=new EUItemListResult((int)info.getTotal(),list);

        return result;

    }

    @Override
    public GoodsResult updateContent(TbContent tc) {
//        if(tc==null)

        tc.setUpdated(new Date());
        mapper.updateByPrimaryKey(tc);

        return new GoodsResult(tc);
    }

    @Override
    public GoodsResult deleteByids(long[] ids) {
        for(long id:ids)
            mapper.deleteByPrimaryKey(id);
        GoodsResult gr=new GoodsResult();
        gr.setStatus(200);
        return gr;
    }

    @Override
    public GoodsResult save(TbContent tc) {
        Date date=new Date();
        tc.setCreated(date);
        tc.setUpdated(date);
        mapper.insert(tc);

        return GoodsResult.ok(tc);
    }
}
