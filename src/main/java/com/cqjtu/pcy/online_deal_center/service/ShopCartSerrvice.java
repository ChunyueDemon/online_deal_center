package com.cqjtu.pcy.online_deal_center.service;


import com.cqjtu.pcy.online_deal_center.common.OrderDetail;

import java.util.List;

public interface ShopCartSerrvice {
    /**
     * 添加一个一个item到购物车
     * @param productId //商品id
     * @param userName //用户名
     * @param attributeId //选择的商品属性id
     * @param purchaseAmount //购买的商品数量
     * @return
     */
    boolean addToCart(Integer productId,String userName,Integer attributeId,int purchaseAmount);

    /**
     * 根据用户名得到用户购物车中的信息
     * @param userName //用户名
     * @return
     */
    List<OrderDetail> getShopCart(String userName);

    /**
     * 根据商品id和用户名删除一项
     * @param item_id //购物车Id
     * @return
     */
    boolean deleteCartItem(Integer item_id);
}
