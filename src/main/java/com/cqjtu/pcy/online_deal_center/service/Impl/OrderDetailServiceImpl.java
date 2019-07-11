package com.cqjtu.pcy.online_deal_center.service.Impl;

import com.cqjtu.pcy.online_deal_center.common.OrderDetail;
import com.cqjtu.pcy.online_deal_center.service.OrderDetailService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据商品id和商品属性id的到所下订单商品的详细信息
     * @param productId
     * @param attributeId
     * @return
     */
    @Override
    public OrderDetail getOrderDetailByProductIdAndAttributeId(Integer productId, Integer attributeId) {
        //SQL
        String sql = "SELECT product.product_id,product.product_name,product_attribute.color,product_attribute.type,product_attribute.price,product_attribute.attribute_id \n" +
                "FROM product,product_attribute \n" +
                "where product.product_id=product_attribute.product_id and product.product_id="+productId+" and product_attribute.attribute_id="+attributeId+"";
        //结果
        OrderDetail products = jdbcTemplate.queryForObject(sql, new RowMapper<OrderDetail>() {
            //映射每行数据
            @Override
            public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderDetail p = new OrderDetail();
                p.setProductId(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setProductPrice(rs.getDouble("price"));
                p.setColor(rs.getString("color"));
                p.setType(rs.getString("type"));
                p.setAttributeId(rs.getInt("attribute_id"));
                return p;
            }
        });
        return products;
    }
}
