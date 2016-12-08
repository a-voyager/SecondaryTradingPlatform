package com.swpuiot.stp.AdapterItem;

/**
 * Created by DELL on 2016/12/6.
 */
public class ShoppingItem {
    private int imgId;
    private String shopname;

    public ShoppingItem(int imgId, String shopname){
        this.imgId = imgId;
        this.shopname = shopname;
    }
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
}
