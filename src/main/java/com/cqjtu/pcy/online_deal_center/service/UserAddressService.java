package com.cqjtu.pcy.online_deal_center.service;

import com.cqjtu.pcy.online_deal_center.dal.entity.UserAddress;

import java.util.List;

public interface UserAddressService {
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
    boolean addAddress(String userName,String region,String addressDetail,String name,String tel,String defaultAddressTag);

    /**
     * 删除收货地址信息
     * @param itemId //地址信息Id
     * @return
     */
    boolean deleteAddress(Integer itemId);

    /**
     *
     * @param userAddress
     */
    boolean modifyAddress(UserAddress userAddress);

    /**get地址信息
     * @param itemId //地址信息Id
     * @return
     */
    UserAddress getAddress(Integer itemId);

    /**
     * 根据userName查询地址信息
     * @param userName
     * @return
     */
    List<UserAddress> getAddressByUserName(String userName);

    /**
     * 将地址Id为itemId设置为默认地址
     * @param itemId
     * @return
     */
    boolean setDefaultAddress(Integer itemId,String userName);
}
