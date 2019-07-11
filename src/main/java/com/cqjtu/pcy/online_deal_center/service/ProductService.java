package com.cqjtu.pcy.online_deal_center.service;

import com.cqjtu.pcy.online_deal_center.dal.entity.Product;

import java.util.List;
public interface ProductService {
    /**
     * 查询出所有的商品信息
     * @return
     */
    List<Product> getProducts();
    /**
     * 根据商品ID查询出商品信息
     * @param productId
     * @return
     */
    Product getProduct(Integer productId);
    /**
     * 根据搜索词从数据库中匹配商品信息
     * @param productName //传入的搜索词
     * @return 返回一个List<Product>
     */
    List<Product> getProductsByProductName(String productName);//根据productName查询商品信息

}
