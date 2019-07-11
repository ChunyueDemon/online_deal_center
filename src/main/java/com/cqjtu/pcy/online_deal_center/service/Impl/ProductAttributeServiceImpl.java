package com.cqjtu.pcy.online_deal_center.service.Impl;


import com.cqjtu.pcy.online_deal_center.common.ColorAmount;
import com.cqjtu.pcy.online_deal_center.common.ColorAndTypeAmount;
import com.cqjtu.pcy.online_deal_center.common.TypeAmount;
import com.cqjtu.pcy.online_deal_center.dal.ProductAttributeRepo;
import com.cqjtu.pcy.online_deal_center.dal.entity.ProductAttribute;
import com.cqjtu.pcy.online_deal_center.service.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    ProductAttributeRepo productAttributeRepo;


    /**
     * 根据商品id的到所有的颜色
     * @param productId //商品id
     * @return
     */
    @Override
    public List<String> getAllColors(Integer productId) {
        String sql = "SELECT DISTINCT color  FROM product_attribute WHERE product_id="+productId;
        //结果
        List<String> colors = jdbcTemplate.query(sql, new RowMapper<String>() {
            //映射每行数据
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("color");
            }
        });
        return colors;
    }

    /**
     * 根据商品id得到所有类型
     * @param productId //商品id
     * @return
     */
    @Override
    public List<String> getAllTypes(Integer productId) {
        String sql = "SELECT DISTINCT type  FROM product_attribute WHERE product_id="+productId;
        //结果
        List<String> types = jdbcTemplate.query(sql, new RowMapper<String>() {
            //映射每行数据
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("type");
            }
        });
        return types;
    }

    /**
     * 计算商品id为productId各种颜色的数量
     * @param productId //商品id
     * @return
     */
    @Override
    public List<ColorAmount> countByColor(Integer productId) {
        List<ColorAmount> countColor=new ArrayList<>();
        String sql = "SELECT color,sum(amount) as amount FROM product_attribute where product_id="+productId+" group by color;";
        //结果
        countColor=jdbcTemplate.query(sql, new RowMapper<ColorAmount>() {
            //映射每行数据
            @Override
            public ColorAmount mapRow(ResultSet rs, int rowNum) throws SQLException {
                ColorAmount colorAmount=new ColorAmount();
                colorAmount.setColor(rs.getString("color"));
                colorAmount.setAmount(rs.getInt("amount"));
                return colorAmount;
            }
        });
        return countColor;
    }

    /**
     * 计算商品id为productId各种type的数量
     * //@param type //商品类型
     * @param productId  //商品id
     * @return
     */
    @Override
    public List<TypeAmount> countByType(Integer productId) {
        List<TypeAmount> countTypes=new ArrayList<>();
        String sql = "SELECT type,sum(amount) as amount FROM product_attribute where product_id="+productId+" group by type;";
        //结果
        countTypes=jdbcTemplate.query(sql, new RowMapper<TypeAmount>() {
            //映射每行数据
            @Override
            public TypeAmount mapRow(ResultSet rs, int rowNum) throws SQLException {
                TypeAmount typeAmount=new TypeAmount();
                typeAmount.setType(rs.getString("type"));
                typeAmount.setAmount(rs.getInt("amount"));
                return typeAmount;
            }
        });
        return countTypes;
    }

    /**
     *
     * @param productId //商品id
     * @return
     */
    @Override
    public List<ProductAttribute> getProductAttributes(Integer productId) {
        return productAttributeRepo.findAllByProductId(productId);
    }

    /**
     * get商品id为productId颜色为color类型为type的价格
     * @param color //商品颜色
     * @param type //商品类型
     * @param productId //商品id
     * @return
     */
    @Override
    public double getPriceByColorAndType(String color, String type, Integer productId) {
        return 0;
    }

    /**
     * 根据属性id的到一个商品属性
     * @param attributeId
     * @return
     */
    @Override
    public ProductAttribute getProductAttribute(Integer attributeId) {
        return productAttributeRepo.findOneByAttributeId(attributeId);
    }
}
