package com.cqjtu.pcy.online_deal_center.dal;

import com.cqjtu.pcy.online_deal_center.dal.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface UserAddressRepo extends JpaRepository<UserAddress,Integer> {
    UserAddress findByItemId(Integer itemId);
    List<UserAddress> findAllByUserName(String userName);
}
