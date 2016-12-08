package com.swpuiot.stp.AdapterItem;

/**
 * Created by DELL on 2016/12/5.
 */
public class MyItem {

    private String title;
    private   int imageId;
    public MyItem(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
