package com.cqjtu.pcy.online_deal_center.web;

import com.cqjtu.pcy.online_deal_center.service.ShopCartSerrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ShopCartController {
    @Autowired
    ShopCartSerrvice shopCartSerrvice;

    /**
     *
     * @param productId
     * @param session
     * @return 0：表示增加成功，1：添加失败，2：未登录
     */
    @RequestMapping("addToCart")
    @ResponseBody
    public int addToCart(String productId,int purchaseAmount,String attributeId, HttpSession session){
        String userName;
        try{
            userName=session.getValue("userName").toString();
        }catch (Exception e){
            return 2;
        }

        if (shopCartSerrvice.addToCart(new Integer(productId),userName,new Integer(attributeId),purchaseAmount))
        return 0;
        else
            return 1;
    }

    @RequestMapping("deleteCartItem")
    @ResponseBody
    public boolean deleteCartItem(String item_id){
        return shopCartSerrvice.deleteCartItem(new Integer(item_id));
    }
}
