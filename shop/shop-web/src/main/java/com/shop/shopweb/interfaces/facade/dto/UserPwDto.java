package com.shop.shopweb.interfaces.facade.dto;

import lombok.Data;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/29 23:40
 */
@Data
public class UserPwDto {
    String userId;
    String oldPw;
    String newPw;
}
