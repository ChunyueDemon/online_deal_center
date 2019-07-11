package com.cqjtu.pcy.online_deal_center.dal.entity;

import javax.persistence.*;

@Entity
@Table(name="userAddress")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    private String userName;//该地址信息的所属者
    private String region;//所在区域
    private String addressDetail;//详细地址
    private String tel;//收件人电话
    private String name;//收货人姓名
    private String defaultAddressTag;//"默认地址"为默认地址，“设为默认”为非默认地址

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultAddressTag() {
        return defaultAddressTag;
    }

    public void setDefaultAddressTag(String defaultAddressTag) {
        this.defaultAddressTag = defaultAddressTag;
    }
}
