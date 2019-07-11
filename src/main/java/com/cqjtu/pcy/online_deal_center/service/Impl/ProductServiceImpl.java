package com.cqjtu.pcy.online_deal_center.service.Impl;

import com.cqjtu.pcy.online_deal_center.dal.ProductRepo;
import com.cqjtu.pcy.online_deal_center.dal.entity.Product;
import com.cqjtu.pcy.online_deal_center.service.ProductService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    ProductRepo productRepo;

    /**
     * 查询出所有的商品信息
     * @return
     */
    @Override
    public List<Product> getProducts() {
        List<Product> products=new ArrayList<>();
        Pageable pageable=new PageRequest(0,10);
        Page<Product> productPage = productRepo.findAll(pageable);
        Iterator<Product> iterator = productPage.iterator();
        while (iterator.hasNext())
            products.add(iterator.next());
        return products;
    }

    /**
     * 根据商品ID查询出商品信息
     * @param productId
     * @return
     */
    @Override
    public Product getProduct(Integer productId) {
        return productRepo.findOneByProductId(productId);
    }

    /**
     * 根据搜索词从数据库中匹配商品信息
     * @param productName //传入的搜索词
     * @return 返回一个List<Product>
     */
    @Override
    public List<Product> getProductsByProductName(String productName) {
        //将搜索词分解成单个字符
        List<String> list= Stream.iterate(0, n -> ++n).limit(productName.length())
                .map(n -> "" + productName.charAt(n))
                .collect(Collectors.toList());

        //拼接查询字符串
        String str="";
        for (String s:
                list
             ) {
            str=str+" or product_name like '%"+s+"%'";
        }
        str=str.substring(4,str.length());
        //SQL
        String sql = "SELECT *  FROM product WHERE "+str;
        //结果
        List<Product> products = jdbcTemplate.query(sql, new RowMapper<Product>() {
            //映射每行数据
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setAvailable(rs.getString("available"));
                p.setProductDescImg(rs.getString("product_desc_img"));
                p.setProductImg(rs.getString("product_img"));
                p.setProductNumber(rs.getInt("product_number"));
                p.setProductPrice(rs.getDouble("product_price"));
                return p;
            }
        });
        return products;
    }
}
