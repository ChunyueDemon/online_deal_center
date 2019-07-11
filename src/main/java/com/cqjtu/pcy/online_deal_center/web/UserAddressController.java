package com.cqjtu.pcy.online_deal_center.web;

import com.cqjtu.pcy.online_deal_center.dal.entity.UserAddress;
import com.cqjtu.pcy.online_deal_center.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserAddressController {
    @Autowired
    UserAddressService userAddressService;
    @RequestMapping("addAddress")
    @ResponseBody
    public List<UserAddress> addAddress(HttpSession session,String province, String city, String county, String addressDetail, String name, String tel, String defaultAddressTag){
        //如果该地址设置为默认地址，将原先的默认地址设置为非默认地址
        if(defaultAddressTag.equals("默认地址")){
            List<UserAddress> address = userAddressService.getAddressByUserName(session.getValue("userName").toString());
            for (UserAddress u :
                    address) {
                if (u.getDefaultAddressTag().equals("默认地址")) {
                    u.setDefaultAddressTag("设为默认");
                    userAddressService.modifyAddress(u);
                    break;
                }
            }
        }
        userAddressService.addAddress(session.getValue("userName").toString(),province+"/"+city+"/"+county,addressDetail,name,tel,defaultAddressTag);
        return userAddressService.getAddressByUserName(session.getValue("userName").toString());
    }

    @RequestMapping("setDefaultAddress")
    @ResponseBody
    public List<UserAddress> setDefaultAddress(String itemId,HttpSession session){
        if (userAddressService.setDefaultAddress(new Integer(itemId),session.getValue("userName").toString())){
            return userAddressService.getAddressByUserName(session.getValue("userName").toString());
        }
        return null;
    }

    @RequestMapping("deleteAddress")
    @ResponseBody
    public List<UserAddress> deleteAddress(HttpSession session, String itemId){
        if (userAddressService.deleteAddress(new Integer(itemId))) {
            return userAddressService.getAddressByUserName(session.getValue("userName").toString());
        }
        return null;
    }

}
