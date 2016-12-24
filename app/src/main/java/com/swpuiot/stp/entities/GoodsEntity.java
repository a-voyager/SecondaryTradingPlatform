package com.swpuiot.stp.entities;

import java.io.Serializable;

/**
 * Created by DELL on 2016/12/19.
 */
public class GoodsEntity implements Serializable{
    private String id; // 商品编号
    private String name; // 名称
    private double price; // 价格
    private String category; // 分类
    private int pnum; // 数量
    private String imgurl; // 图片路径
    private String description; // 描述
    // 在获取销售榜单信息时使用
    private int totalSaleNum; // 总销售数量

    private String dealps;
    private boolean discount;
    private String QQ;
    private String phone;
    private int owner;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDealps() {
        return dealps;
    }

    public void setDealps(String dealps) {
        this.dealps = dealps;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public int getTotalSaleNum() {
        return totalSaleNum;
    }

    public void setTotalSaleNum(int totalSaleNum) {
        this.totalSaleNum = totalSaleNum;
    }

    @Override
    public String toString() {
        return "GoodsEntity{" +
                "category='" + category + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", pnum=" + pnum +
                ", imgurl='" + imgurl + '\'' +
                ", description='" + description + '\'' +
                ", totalSaleNum=" + totalSaleNum +
                ", dealps='" + dealps + '\'' +
                ", discount=" + discount +
                ", QQ='" + QQ + '\'' +
                ", phone='" + phone + '\'' +
                ", owner=" + owner +
                '}';
    }
}
