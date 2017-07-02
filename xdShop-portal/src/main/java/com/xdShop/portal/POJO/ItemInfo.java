package com.xdShop.portal.POJO;

import com.xdShop.pojo.TbItem;

/**
 * Created by pro on 17/2/21.
 * 封装了商品详情页面 item.jsp 所需要的pojo
 */
public class ItemInfo extends TbItem{

    public String[] getImages(){
        String image=getImage();
        if(image!=null){
            String images[]=image.split(",");
            return images;
        }
        return null;
    }
}
