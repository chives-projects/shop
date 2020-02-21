package com.shop.shopweb.repo;

import com.shop.shopweb.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    //    public User findByAccountAndRoleAndPassword(String account,Integer role,String password);
    public User findByUserIdAndRoleAndPassword(String userId, Integer role, String password);
//    public List<User> findByRole(Integer role);
//    public User findByAccount(String account);
}
