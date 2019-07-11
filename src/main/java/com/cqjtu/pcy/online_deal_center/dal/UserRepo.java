package com.cqjtu.pcy.online_deal_center.dal;

import com.cqjtu.pcy.online_deal_center.dal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    boolean existsByUserNameAndUserPassword(String userName,String password);
    boolean existsByUserName(String userName);
    boolean existsByUserEmail(String userName);
}
