package com.cqjtu.pcy.online_deal_center.service.Impl;

import com.cqjtu.pcy.online_deal_center.common.OrderDetail;
import com.cqjtu.pcy.online_deal_center.dal.ProductRepo;
import com.cqjtu.pcy.online_deal_center.dal.ShopCartRepo;
import com.cqjtu.pcy.online_deal_center.dal.entity.ShopCart;
import com.cqjtu.pcy.online_deal_center.service.ShopCartSerrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopCartServiceImpl implements ShopCartSerrvice {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    ShopCartRepo shopCartRepo;
    @Autowired
    ProductRepo productRepo;

    /**
     * 添加一个一个item到购物车
     * @param productId //商品id
     * @param userName //用户名
     * @param attributeId //选择的商品属性id
     * @param purchaseAmount //购买的商品数量
     * @return
     */
    @Override
    public boolean addToCart(Integer productId, String userName,Integer attributeId,int purchaseAmount) {
        try{
            ShopCart shopCart=new ShopCart();
            shopCart.setProductId(productId);
            shopCart.setAddingTime(new Date());
            shopCart.setUserName(userName);
            shopCart.setAttributeId(attributeId);
            shopCart.setPurchaseAmount(purchaseAmount);
            shopCartRepo.save(shopCart);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    /**
     * 根据用户名得到用户购物车中的信息
     * @param userName //用户名
     * @return
     */
    @Override
    public List<OrderDetail> getShopCart(String userName) {
        //SQL
        String sql = "SELECT shop_cart.user_name,product.product_id,shop_cart.item_id,shop_cart.attribute_id,product.product_img,product.product_name,product_attribute.color,product_attribute.type,product_attribute.price,shop_cart.purchase_amount  \n" +
                "FROM product,shop_cart,product_attribute \n" +
                "where product.product_id=shop_cart.product_id and shop_cart.attribute_id=product_attribute.attribute_id  and shop_cart.user_name='"+userName+"'";
        //结果
        List<OrderDetail> products = jdbcTemplate.query(sql, new RowMapper<OrderDetail>() {
            //映射每行数据
            @Override
            public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderDetail p = new OrderDetail();
                p.setProductId(rs.getInt("product_id"));
                p.setItemId(rs.getInt("item_id"));
                p.setProductName(rs.getString("product_name"));
                p.setProductImg(rs.getString("product_img"));
                p.setPurchaseAmount(rs.getInt("purchase_amount"));
                p.setProductPrice(rs.getDouble("price"));
                p.setColor(rs.getString("color"));
                p.setType(rs.getString("type"));
                p.setAttributeId(rs.getInt("attribute_id"));
                return p;
            }
        });
        return products;
    }


    /**
     * 根据商品id和用户名删除一项
     * @param item_id //购物车Id
     * @return
     */
    @Override
    public boolean deleteCartItem(Integer item_id) {
        try {
            shopCartRepo.deleteById(item_id);
        }catch (Exception e){
            return false;
        }
        return true;
    }



}
