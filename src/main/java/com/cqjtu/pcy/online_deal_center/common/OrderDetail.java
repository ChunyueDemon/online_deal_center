package com.cqjtu.pcy.online_deal_center.common;


public class OrderDetail {
    private Integer productId;
    private String productName;
    private int purchaseAmount;
    private double productPrice;
    private String productImg;//商品的封面图
    private Integer itemId;
    private String color;
    private String type;
    private Integer attributeId;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", purchaseAmount=" + purchaseAmount +
                ", productPrice=" + productPrice +
                ", productImg='" + productImg + '\'' +
                ", itemId=" + itemId +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", attributeId=" + attributeId +
                '}';
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
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

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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
}
