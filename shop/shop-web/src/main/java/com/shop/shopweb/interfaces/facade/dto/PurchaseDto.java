package com.shop.shopweb.interfaces.facade.dto;

import lombok.Data;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/29 17:39
 */
@Data
public class PurchaseDto {
    String id;
    String goodsId;
    String supplierId;
    float goodsPrice;
    float sellPrice;
    Integer num;
    String userId;
}
