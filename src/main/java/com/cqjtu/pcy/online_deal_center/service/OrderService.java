package com.cqjtu.pcy.online_deal_center.service;

import com.cqjtu.pcy.online_deal_center.dal.entity.Order;

import java.util.List;

public interface OrderService {
    boolean addOrder(Integer productId,Integer attributeId,Integer addressId,int purchaseAmount,String userName);
    Order getOrderByOrderIdAndUserName(Integer orderId,String userName);
    List<Order> getOrdersByUserName(String userName);
}
