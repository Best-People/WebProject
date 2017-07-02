package com.xdShop.portal.POJO;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by pro on 17/2/22.
 */
public class CartItem {

    private long itemId;

    private long id;

    private long price;

    private int num;

    private String title;

    private String image;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
        this.id=itemId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @JsonIgnore
    public String[] getImages(){
        String images[]=null;
        if(image!=null)
            images=image.split(",");
        return images;
    }
}
