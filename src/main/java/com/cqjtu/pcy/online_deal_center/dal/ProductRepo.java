package com.cqjtu.pcy.online_deal_center.dal;

import com.cqjtu.pcy.online_deal_center.dal.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface ProductRepo extends JpaRepository<Product,Integer> {
    Product findOneByProductId(Integer productid);
    Page<Product> findAll(Pageable pageable);


}
