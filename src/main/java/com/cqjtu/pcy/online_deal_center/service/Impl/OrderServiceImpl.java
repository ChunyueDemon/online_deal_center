package com.cqjtu.pcy.online_deal_center.service.Impl;

import com.cqjtu.pcy.online_deal_center.dal.OrderRepo;
import com.cqjtu.pcy.online_deal_center.dal.entity.Order;
import com.cqjtu.pcy.online_deal_center.dal.entity.Product;
import com.cqjtu.pcy.online_deal_center.dal.entity.ProductAttribute;
import com.cqjtu.pcy.online_deal_center.service.OrderService;
import com.cqjtu.pcy.online_deal_center.service.ProductAttributeService;
import com.cqjtu.pcy.online_deal_center.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductAttributeService productAttributeService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepo orderRepo;
    /**
     * 添加订单信息
     * @param productId //所购买商品id
     * @param attributeId //所购买商品的属性id
     * @param purchaseAmount //所购买的数量
     * @param userName //购买者
     * @return
     */
    @Override
    public boolean addOrder(Integer productId, Integer attributeId,Integer addressId,int purchaseAmount, String userName) {
        try{
            Product product = productService.getProduct(productId);
            ProductAttribute productAttribute = productAttributeService.getProductAttribute(attributeId);
            Order order=new Order();
            order.setProductId(product.getProductId());
            order.setUserName(userName);
            order.setAttributeId(productAttribute.getAttributeId());
            order.setOrderAmount(productAttribute.getPrice()*purchaseAmount);
            order.setProductAmount(purchaseAmount);
            order.setOrderCreationTime(new Date());
            order.setItemId(addressId);
            orderRepo.save(order);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据订单id和用户名get订单
     * @param orderId //订单id
     * @param userName //购买者
     * @return
     */
    @Override
    public Order getOrderByOrderIdAndUserName(Integer orderId, String userName) {
        return null;
    }

    /**
     * 根据用户名得到一个订单List
     * @param userName //购买者
     * @return
     */
    @Override
    public List<Order> getOrdersByUserName(String userName) {
        return null;
    }
}
