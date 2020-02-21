package com.shop.shopweb.controller;

import com.shop.shopweb.interfaces.facade.dto.UserPwDto;
import com.shop.shopweb.po.User;
import com.shop.shopweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/25 14:56
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public User getByKey(@PathVariable String key) {
        return userService.findBykey(key);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public boolean updateUserPwd(@RequestBody UserPwDto userPwDto, HttpServletResponse response) {
        if (!userService.updateUserPwd(userPwDto))
            response.setStatus(419);
        return userService.updateUserPwd(userPwDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable String id, HttpServletResponse response) {
        return userService.deleteById(id);
    }
}
