package com.cqjtu.pcy.online_deal_center.service.Impl;

import com.cqjtu.pcy.online_deal_center.dal.UserAddressRepo;
import com.cqjtu.pcy.online_deal_center.dal.entity.UserAddress;
import com.cqjtu.pcy.online_deal_center.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    UserAddressRepo userAddressRepo;
    /**
     * 添加收货地址信息
     * @param userName //地址的信息的创建者
     * @param region //收货人所在区
     * @param addressDetail //详细地址
     * @param name //收货人姓名
     * @param tel //收获人电话
     * @param defaultAddressTag //默认收货地址标记
     * @return
     */
    @Override
    public boolean addAddress(String userName, String region, String addressDetail, String name, String tel, String defaultAddressTag) {
        try{
            UserAddress userAddress=new UserAddress();
            userAddress.setUserName(userName);
            userAddress.setRegion(region);
            userAddress.setAddressDetail(addressDetail);
            userAddress.setName(name);
            userAddress.setTel(tel);
            userAddress.setDefaultAddressTag(defaultAddressTag);
            userAddressRepo.save(userAddress);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除收货地址信息
     * @param itemId //地址信息Id
     * @return
     */
    @Override
    public boolean deleteAddress(Integer itemId) {
        try{
            userAddressRepo.deleteById(itemId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @param userAddress
     * @return //默认收货地址标记
     */
    @Override
    public boolean modifyAddress(UserAddress userAddress) {
        try {
            userAddressRepo.save(userAddress);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**get地址信息
     * @param itemId //地址信息Id
     * @return
     */
    @Override
    public UserAddress getAddress(Integer itemId) {
        return userAddressRepo.findByItemId(itemId);
    }

    /**
     * 根据userName查询地址信息
     * @param userName
     * @return
     */
    @Override
    public List<UserAddress> getAddressByUserName(String userName) {
        return userAddressRepo.findAllByUserName(userName);
    }

    /**
     * 将地址Id为itemId设置为默认地址
     * @param itemId
     * @return
     */
    @Override
    public boolean setDefaultAddress(Integer itemId, String userName) {
        try {
            List<UserAddress> allByUserName = userAddressRepo.findAllByUserName(userName);
            for (UserAddress u :
                    allByUserName) {
                if (u.getDefaultAddressTag().equals("默认地址")) {
                    u.setDefaultAddressTag("设为默认");
                    UserAddress address = userAddressRepo.findByItemId(itemId);
                    address.setDefaultAddressTag("默认地址");
                    userAddressRepo.save(address);
                    break;
                }
            }
            UserAddress temp=userAddressRepo.findByItemId(itemId);
            temp.setDefaultAddressTag("默认地址");
            userAddressRepo.save(temp);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
