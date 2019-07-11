package com.cqjtu.pcy.online_deal_center.dal.entity;

import javax.persistence.*;

@Entity
@Table(name="productAttribute")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attributeId;
    private String color;//商品的颜色分类
    private String type;//商品的类型分类，例如衣服的大小，物品的不同类型
    private int amount;//该种颜色型号的商品数量
    private double price;//该种型号的商品的价格
    private Integer productId;//所属的商品id

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
