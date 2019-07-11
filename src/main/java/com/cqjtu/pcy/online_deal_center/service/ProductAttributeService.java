package com.cqjtu.pcy.online_deal_center.service;




import com.cqjtu.pcy.online_deal_center.common.ColorAmount;
import com.cqjtu.pcy.online_deal_center.common.ColorAndTypeAmount;
import com.cqjtu.pcy.online_deal_center.common.TypeAmount;
import com.cqjtu.pcy.online_deal_center.dal.entity.ProductAttribute;

import java.util.List;
import java.util.Map;

public interface ProductAttributeService {
    List<String> getAllColors(Integer productId);
    List<String> getAllTypes(Integer productId);
    List<ColorAmount> countByColor(Integer productId);
    List<TypeAmount> countByType(Integer productId);
    List<ProductAttribute> getProductAttributes(Integer productId);
    double getPriceByColorAndType(String color,String type,Integer productId);
    ProductAttribute getProductAttribute(Integer attributeId);

}
