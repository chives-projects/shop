package com.shop.shopweb.interfaces.facade.dto;

import lombok.Data;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/30 1:19
 */
@Data
public class PurchaseReDto {
    String goodsId;
    String supplierId;
    String userId;
    float goodsPrice;
    Integer num;
}
