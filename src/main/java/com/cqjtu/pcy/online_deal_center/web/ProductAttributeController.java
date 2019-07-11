package com.cqjtu.pcy.online_deal_center.web;

import com.cqjtu.pcy.online_deal_center.common.ColorAmount;
import com.cqjtu.pcy.online_deal_center.common.TypeAmount;
import com.cqjtu.pcy.online_deal_center.dal.entity.ProductAttribute;
import com.cqjtu.pcy.online_deal_center.service.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductAttributeController {

    @Autowired
    ProductAttributeService productAttributeService;
    @RequestMapping("getProductAttributes")
    @ResponseBody
    public List<ProductAttribute> getProductAttributes(String productId){
        return productAttributeService.getProductAttributes(new Integer(productId));
    }

    @RequestMapping("getColorAmount")
    @ResponseBody
    public List<ColorAmount> getColorAmount(String productId){
        return productAttributeService.countByColor(new Integer(productId));
    }

    @RequestMapping("getTypeAmount")
    @ResponseBody
    public List<TypeAmount> getTypeAmount(String productId){
        return productAttributeService.countByType(new Integer(productId));
    }
}
