package com.swpuiot.stp.AdapterItem;


/**
 * Created by DELL on 2016/11/30.
 */
public class LoginedItem {
    private String text;
    private int imgId;

    public LoginedItem(String text, int imgId) {
        this.text = text;
        this.imgId = imgId;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
