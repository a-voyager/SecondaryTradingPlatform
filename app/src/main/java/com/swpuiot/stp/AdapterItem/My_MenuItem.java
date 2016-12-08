package com.swpuiot.stp.AdapterItem;

/**
 * Created by DELL on 2016/12/5.
 */
public class My_MenuItem {
    private String title;
    private   int imageId;
    public My_MenuItem(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
