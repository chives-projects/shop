package com.shop.shopweb.service;

import com.shop.shopweb.interfaces.facade.dto.UserPwDto;
import com.shop.shopweb.po.User;

import java.util.List;

public interface UserService {
    public User findById(String id);

    public User findByAccountAndRoleAndPassword(String account,Integer role,String password);

    public User findByUserIdAndRoleAndPassword(User user);

    public List<User> findAll();

    public User findBykey(String key);

    public boolean addUser(User user);

    public boolean updateUser(User user);

    public boolean deleteById(String userId);

    boolean updateUserPwd(UserPwDto userPwDto);
}
