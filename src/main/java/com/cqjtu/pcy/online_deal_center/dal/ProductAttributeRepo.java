package com.cqjtu.pcy.online_deal_center.dal;


import com.cqjtu.pcy.online_deal_center.dal.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductAttributeRepo extends JpaRepository<ProductAttribute,Integer> {
    ProductAttribute findOneByAttributeId(Integer attributeId);
    List<ProductAttribute> findByProductId(Integer productId);
    void deleteByProductId(Integer productId);
    List<ProductAttribute> findAllByProductId(Integer productId);
}
