package com.shop.common.res;

import lombok.Data;

/**
 * 返回结果类
 * @param <T>
 */
@Data
public class BaseResponse<T> {

    private Integer code;

    private String message;

    private T data;
    public BaseResponse(){}

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
