package com.shop.shopweb.interfaces.facade.dto;

import com.shop.common.base.BaseDTO;
import com.shop.shopweb.interfaces.assembler.GoodsAssembler;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/24 23:39
 */
@Data
public class OrdersAddDto {
    String userId;
    List<GoodsAssembler> items;
    float actualPayment;
}
