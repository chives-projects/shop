package com.shop.shopweb.service.impl;

import com.shop.common.util.EncryptUtils;
import com.shop.shopweb.interfaces.facade.dto.UserPwDto;
import com.shop.shopweb.po.User;
import com.shop.shopweb.repo.UserRepository;
import com.shop.shopweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/23 12:07
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByAccountAndRoleAndPassword(String account, Integer role, String password) {
        return null;//userRepository.findByAccountAndRoleAndPassword(account, role, password);
    }

    @Override
    public User findByUserIdAndRoleAndPassword(User user) {
        System.out.println(user);
        System.out.println(EncryptUtils.encryptPassword(user.getPassword()) );
        return userRepository.findByUserIdAndRoleAndPassword(user.getUserId(),
                user.getRole(),EncryptUtils.encryptPassword(user.getPassword()));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findBykey(String key) {
        return userRepository.findById(key).get();
    }

    @Override
    public boolean addUser(User user) {
        System.out.println(user);
        Optional<User> optional = userRepository.findById(user.getUserId());
        System.out.println(optional.isPresent());
        User temp = optional.isPresent()?optional.get():null;
        // 已经存在
        if (temp != null) return false;
        user.setPassword(EncryptUtils.encryptPassword(user.getPassword()));
        System.out.println(user.getPassword());
        userRepository.save(user);
        return true;
    }
    @Override
    public boolean updateUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteById(String userId) {
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public boolean updateUserPwd(UserPwDto userPwDto) {
        String userId = userPwDto.getUserId();
        User user=userRepository.findById(userId).get();
        System.out.println(user.getPassword());
        System.out.println(EncryptUtils.encryptPassword(userPwDto.getOldPw()));
        if (user.getPassword().equals(EncryptUtils.encryptPassword(userPwDto.getOldPw())))
            user.setPassword(EncryptUtils.encryptPassword(userPwDto.getNewPw()));
        else return false;
        return true;
    }
}
