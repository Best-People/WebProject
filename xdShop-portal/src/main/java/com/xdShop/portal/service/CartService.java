package com.xdShop.portal.service;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.portal.POJO.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by pro on 17/2/22.
 */
public interface CartService {

    GoodsResult addCartItem(long itemId, int numt, HttpServletRequest request, HttpServletResponse response);

    List<CartItem> getCartItemList(HttpServletRequest request,HttpServletResponse response);

    GoodsResult deleteById(HttpServletRequest request,HttpServletResponse response,Long itemId);


//    GoodsResult updateCart(HttpServletRequest request,HttpServletResponse response,long itemId,int num);
}
