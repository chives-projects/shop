package com.shop.shopweb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.common.enums.ResultEnum;
import com.shop.common.res.BaseResponse;
import com.shop.shopweb.config.token.TokenService;
import com.shop.shopweb.config.token.TokenUtil;
import com.shop.shopweb.config.token.UserLoginToken;
import com.shop.shopweb.po.User;
import com.shop.shopweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/16 1:41
 */
@RestController
//@RequestMapping("/login")
public class LoginController {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;

    @RequestMapping("/toLogin")
    public String ToLogin() {
        return "/login";
    }


    @RequestMapping("/login")
//    public Object Login(@RequestBody String userDto, HttpServletResponse response) {
    public String Login(@RequestBody User user, HttpServletResponse response) {
//    public BaseResponse Login(@RequestBody User user, HttpServletResponse response) {
        user = userService.findByUserIdAndRoleAndPassword(user);
        String token = null;
        if (user == null) {
            response.setStatus(418);
            return null;
        }
        token = tokenService.getToken(user);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
//        session.setAttribute("to",token);
        return token;
    }

    /***
     * 这个请求需要验证token才能访问
     * @return String 返回类型
     */
    @UserLoginToken
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public String getMessage() {
        // 取出token中带的用户id 进行操作
        System.out.println(TokenUtil.getTokenUserId());
        return "您已通过验证";
    }

}
