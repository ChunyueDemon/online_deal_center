package com.cqjtu.pcy.online_deal_center.service.Impl;

import com.cqjtu.pcy.online_deal_center.dal.UserRepo;
import com.cqjtu.pcy.online_deal_center.dal.entity.User;
import com.cqjtu.pcy.online_deal_center.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginAndRegisterServiceImpl implements LoginAndRegisterService {
    @Autowired
    UserRepo userRepo;

    /**
     * 添加一个用户
     * @param userName //用户名
     * @param password //用户密码
     * @param email //用户email
     * @return
     */
    @Override
    public boolean addUser(String userName, String password, String email) {
        try{
            User user=new User();
            user.setUserName(userName);
            user.setUserPassword(password);
            user.setUserEmail(email);
            userRepo.save(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 判断用户名和密码是否匹配
     * @param userName //用户名
     * @param password //用户密码
     * @return
     */
    @Override
    public boolean userNameMatchPassword(String userName, String password) {
        boolean existsByUserNameAndUserPassword = userRepo.existsByUserNameAndUserPassword(userName, password);
        if(existsByUserNameAndUserPassword)
            return true;
        else
            return false;

    }

    /**
     * 判断userName是否存在，存在返回true
     * @param userName //用户名
     * @return
     */
    @Override
    public boolean existsByUserName(String userName) {
        return userRepo.existsByUserName(userName);
    }

    /**
     * 判断email是否存在，存在返回true
     * @param userEmail //email
     * @return
     */
    @Override
    public boolean existsByUserEmail(String userEmail) {
        return userRepo.existsByUserEmail(userEmail);
    }
}
