package com.shop.common.enums;

import lombok.Getter;

/**
 * 返回结果的枚举类
 */
@Getter
public enum ResultEnum {

    SUCCESS(0, "success"),
    NOT_NETWORK(1, "系统繁忙，请稍后再试"),
    LOGIN_ERROR(2, "账号密码错误"),
    LOGIN_VERIFY_FALL(3, "登录失效"),
    PARAM_VERIFY_FALL(4, "参数验证错误"),
    AUTH_FAILED(5, "权限验证失败"),
    DATA_NOT(6, "没有相关数据"),
    DATA_CHANGE(7, "数据没有任何更改"),
    DATA_REPEAT(8, "数据已存在"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
