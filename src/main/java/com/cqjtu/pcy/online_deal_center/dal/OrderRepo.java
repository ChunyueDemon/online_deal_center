package com.cqjtu.pcy.online_deal_center.dal;

import com.cqjtu.pcy.online_deal_center.dal.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Integer> {
}
