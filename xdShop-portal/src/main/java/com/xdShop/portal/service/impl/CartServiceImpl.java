package com.xdShop.portal.service.impl;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.common.utils.CookieUtils;
import com.xdShop.common.utils.ExceptionUtil;
import com.xdShop.common.utils.HttpClientUtil;
import com.xdShop.common.utils.JsonUtils;
import com.xdShop.pojo.TbItem;
import com.xdShop.pojo.TbUserExample;
import com.xdShop.portal.POJO.CartItem;
import com.xdShop.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 17/2/22.
 */
@Service
public class CartServiceImpl implements CartService {

    @Value("${REST_BASE_PATH}")
    private String REST_BASE_PATH;

    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;


    @Override
    public GoodsResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {

        CartItem cartItem = null;

        List<CartItem> list=getCartItemList(request,response);

        if(list!=null&&list.size()>0){
            for(CartItem c:list){
                if(c.getItemId()==itemId){
                    c.setNum(c.getNum()+num);
                    cartItem=c;
                    break;
                }
            }
        }
        //购物车中没有此商品
        if(cartItem==null){
            cartItem=new CartItem();
            try {
                //调用rest获取商品信息
                String itemInfoJson= HttpClientUtil.doGet(REST_BASE_PATH+ITEM_INFO_URL+itemId);
                GoodsResult result=GoodsResult.formatToPojo(itemInfoJson,TbItem.class);
                if(result.getStatus()==200){
                    TbItem item= (TbItem) result.getData();
                    cartItem.setNum(num);
                    cartItem.setImage(item.getImage());
                    cartItem.setItemId(itemId);
                    cartItem.setPrice(item.getPrice());
                    cartItem.setTitle(item.getTitle());
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return GoodsResult.build(500, ExceptionUtil.getStackTrace(e));
            }
            list.add(cartItem);
        }
        //将购物车加入cookie
        CookieUtils.setCookie(request,response,"TT_CART", JsonUtils.objectToJson(list),true);
        //购物车已存在该商品
        return GoodsResult.ok(cartItem);
    }

    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request,HttpServletResponse response) {
        //从本地cookie取购物车
        String cartJson=CookieUtils.getCookieValue(request,"TT_CART",true);
        if(cartJson!=null){
            List<CartItem> cartItemList=JsonUtils.jsonToList(cartJson,CartItem.class);
            if(cartItemList!=null)
                return cartItemList;
        }
        return new ArrayList<>();

    }

    @Override
    public GoodsResult deleteById(HttpServletRequest request, HttpServletResponse response, Long itemId) {
        List<CartItem> list=getCartItemList(request,response);
        if(list!=null&&list.size()>0){
            for (int i = 0; i <list.size() ; i++) {
                CartItem cartItem=list.get(i);
                if(cartItem.getItemId()==itemId){
                    list.remove(i);
                    CookieUtils.setCookie(request,response,"TT_CART", JsonUtils.objectToJson(list),true);
                    return GoodsResult.ok(null);
                }
            }
        }
        return GoodsResult.build(400,"购物车已无此商品");
    }


//    @Override
//    public GoodsResult updateCart(HttpServletRequest request,HttpServletResponse response,long itemId, int num) {
//        addCartItem(itemId,num,request,response)
//        return null;
//    }
}
