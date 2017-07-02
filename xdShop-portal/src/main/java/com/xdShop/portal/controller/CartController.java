package com.xdShop.portal.controller;

import com.xdShop.common.pojo.GoodsResult;
import com.xdShop.portal.POJO.CartItem;
import com.xdShop.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by pro on 17/2/22.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId,
                              @RequestParam(defaultValue = "1") int num,
                              HttpServletRequest request, HttpServletResponse response){

        GoodsResult result=cartService.addCartItem(itemId,num,request,response);
        if(result.getStatus()==200)
            return "redirect:/cart/cart.html";
        return "cartFault";
    }


    @RequestMapping("/cart")
    public String  getCartItemList(HttpServletRequest request,
                                          HttpServletResponse response,
                                          Model model){
        List<CartItem> list=cartService.getCartItemList(request,response);
        model.addAttribute("cartList",list);
        return "cart";
    }

    @RequestMapping("/update/num/{itemId}/{num}")
    public String updateCart(@PathVariable Long itemId,@PathVariable int num,
                             HttpServletRequest request,HttpServletResponse response){
        cartService.addCartItem(itemId,num,request,response);
        return "cart";
    }

    @RequestMapping("/delete/{itemId}")
    public String delete(@PathVariable Long itemId,
                         HttpServletRequest request,HttpServletResponse response){
        cartService.deleteById(request,response,itemId);
        return "redirect:/cart/cart.html";
    }
    @RequestMapping("/success")
    public String showCart(){
        return "success";
    }
}
