package com.cqjtu.pcy.online_deal_center.dal.entity;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private int productNumber;
    private double productPrice;
    private String productImg;//商品的封面图
    private String productDescImg;//商品的介绍图片
    private String available;//在售’是‘，下架’否‘

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductDescImg() {
        return productDescImg;
    }

    public void setProductDescImg(String productDescImg) {
        this.productDescImg = productDescImg;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productNumber=" + productNumber +
                ", productPrice=" + productPrice +
                ", productImg='" + productImg + '\'' +
                ", productDescImg='" + productDescImg + '\'' +
                '}';
    }
}
