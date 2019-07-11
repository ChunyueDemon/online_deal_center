package com.cqjtu.pcy.online_deal_center.web;

import com.cqjtu.pcy.online_deal_center.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @RequestMapping("submitOrder")
    public String submitOrder(String productId, String attributeId, String addressId, String purchaseAmount, HttpSession session){
        orderService.addOrder(new Integer(productId),new Integer(attributeId),new Integer(addressId), Integer.parseInt(purchaseAmount),session.getValue("userName").toString());
        return "redirect:test.html";
    }
}
