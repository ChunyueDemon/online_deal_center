package com.cqjtu.pcy.online_deal_center.dal;

import com.cqjtu.pcy.online_deal_center.dal.entity.ShopCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShopCartRepo extends JpaRepository<ShopCart,Integer> {
    boolean existsByUserNameAndProductId(String userName,Integer productId);
    List<ShopCart> findAllByUserName(String userName);
    @Modifying
    @Transactional
    @Query(value = "delete from  shop_cart where product_id=:productId and user_name=:userName", nativeQuery = true)
    int deleteItem(@Param("productId") Integer productId,@Param("userName") String userName);


}
