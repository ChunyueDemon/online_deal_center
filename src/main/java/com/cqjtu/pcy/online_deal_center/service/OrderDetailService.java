package com.cqjtu.pcy.online_deal_center.service;

import com.cqjtu.pcy.online_deal_center.common.OrderDetail;

public interface OrderDetailService {
    OrderDetail getOrderDetailByProductIdAndAttributeId(Integer productId,Integer attributeId);
}
