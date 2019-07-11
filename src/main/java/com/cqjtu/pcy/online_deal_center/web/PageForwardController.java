package com.cqjtu.pcy.online_deal_center.web;

import com.cqjtu.pcy.online_deal_center.common.OrderDetail;
import com.cqjtu.pcy.online_deal_center.dal.entity.Product;
import com.cqjtu.pcy.online_deal_center.dal.entity.UserAddress;
import com.cqjtu.pcy.online_deal_center.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PageForwardController {
    @Value("${static.server.address}")
    private String serverAddress;
    @RequestMapping("about.html")
    public String about(){
        return "about";
    }

    @RequestMapping("buytoday.html")
    public String buytoday(){
        return "buytoday";
    }

    @RequestMapping("commodity.html")
    public String commodity(String title,ModelMap modelMap){
        //根据搜索词查询出商品信息并打印到页面
        List<Product> products = productService.getProductsByProductName(title);
        modelMap.put("products",products);
        modelMap.put("serverAddress",serverAddress);
        return "commodity";
    }

    @Autowired
    ProductAttributeService productAttributeService;
    @RequestMapping("details.html")
    public String details(ModelMap modelMap,String productId){
        //将商品信息加载到页面
        Product product = productService.getProduct(new Integer(productId));
        modelMap.put("productName",product.getProductName());
        modelMap.put("productPrice",product.getProductPrice());
        modelMap.put("productImg",product.getProductImg());
        modelMap.put("productDescImg",product.getProductDescImg());
        modelMap.put("productId",product.getProductId());
        return "details";
    }

    @Autowired
    ProductService productService;

    @RequestMapping("index.html")
    public String index(ModelMap modelMap){
        List<Product> products = productService.getProducts();
        modelMap.put("products",products);
        return "index";
    }

    @RequestMapping("information.html")
    public String information(){
        return "information";
    }

    @RequestMapping("login.html")
    public String login(ModelMap modelMap){
        modelMap.put("userName","");
        modelMap.put("info","请输入密码");
        return "login";
    }

    @Autowired
    ShopCartSerrvice shopCartSerrvice;

    @RequestMapping("shopcart.html")
    public String shopcart(ModelMap modelMap, HttpSession session){
        List<OrderDetail> products = shopCartSerrvice.getShopCart(session.getValue("userName").toString());
        for (OrderDetail o :
                products) {
            System.out.println(o.toString());
        }
        modelMap.put("products",products);
        return "shopcart";
    }

    @RequestMapping("register.html")
    public String register(){
        return "register";
    }

    @Autowired
    MailService mailService;

//    @RequestMapping("test")
//    public String test(){
//        mailService.sendHtmlMail("1526602637@qq.com","在线交易中心","您的验证码为"+"111111");
//        return "index";
//    }

    @Autowired
    UserAddressService userAddressService;
    @RequestMapping("personal_center.html")
    public String personalCenter(ModelMap modelMap,HttpSession session){
        List<UserAddress> addressInfos = userAddressService.getAddressByUserName(session.getValue("userName").toString());
        modelMap.put("addressInfos",addressInfos);
        return "personal_center";
    }


    @Autowired
    OrderDetailService orderDetailService;
    @RequestMapping("pay.html")
    public String pay(HttpSession session,ModelMap modelMap,String productId,String attributeId,String purchaseAmount){
        //查询出地址信息
        List<UserAddress> address = userAddressService.getAddressByUserName(session.getValue("userName").toString());
        List<UserAddress> defaultAddress=new ArrayList<>();
        for (int i=0;i<address.size();i++){
            if(address.get(i).getDefaultAddressTag().equals("默认地址")){
                defaultAddress.add(address.get(i));
                address.remove(i);
                break;
            }
        }
        modelMap.put("address",address);
        modelMap.put("defaultAddress",defaultAddress);
        //查询出订单信息
        OrderDetail orderDetail = orderDetailService.getOrderDetailByProductIdAndAttributeId(new Integer(productId), new Integer(attributeId));
        modelMap.put("productName",orderDetail.getProductName());
        modelMap.put("productAttribute",orderDetail.getColor()+" "+orderDetail.getType());
        modelMap.put("price",orderDetail.getProductPrice());
        modelMap.put("amount",purchaseAmount);
        modelMap.put("subAmount",Integer.parseInt(purchaseAmount)*orderDetail.getProductPrice());
        modelMap.put("productId",productId);
        modelMap.put("attributeId",attributeId);
        modelMap.put("addressId",defaultAddress.get(0).getItemId());
        modelMap.put("purchaseAmount",purchaseAmount);
        return "pay";
    }

    @RequestMapping("purchaseProduct")
    public String purchaseProduct(String productId,String attributeId,String color,String type,String purchaseAmount){
        return "redirect:pay.html?productId="+productId+"&attributeId="+attributeId+"&purchaseAmount="+purchaseAmount;
    }

    @RequestMapping("test.html")
    public String test1(){
        return "test";
    }
}
