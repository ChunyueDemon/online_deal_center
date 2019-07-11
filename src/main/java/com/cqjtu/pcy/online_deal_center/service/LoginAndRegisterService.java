package com.cqjtu.pcy.online_deal_center.service;

public interface LoginAndRegisterService {
    /**
     * 添加一个用户
     * @param userName //用户名
     * @param password //用户密码
     * @param email //用户email
     * @return
     */
    boolean addUser(String userName,String password,String email);

    /**
     * 判断用户名和密码是否匹配
     * @param userName //用户名
     * @param password //用户密码
     * @return
     */
    boolean userNameMatchPassword(String userName,String password);

    /**
     * 判断userName是否存在，存在返回true
     * @param userName //用户名
     * @return
     */
    boolean existsByUserName(String userName);

    /**
     * 判断email是否存在，存在返回true
     * @param userEmail //email
     * @return
     */
    boolean existsByUserEmail(String userEmail);


}
