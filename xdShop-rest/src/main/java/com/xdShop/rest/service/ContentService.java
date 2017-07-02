package com.xdShop.rest.service;

import com.xdShop.pojo.TbContent;

import java.util.List;

/**
 * Created by pro on 16/11/12.
 */
public interface ContentService {

    List<TbContent> getContenList(long categoryId);
}
