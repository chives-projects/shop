package com.shop.shopweb.interfaces.facade.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/27 18:22
 */
@Data
public class OrdersRevenueDto implements Serializable {
    String ct;
    float revenue;

    public OrdersRevenueDto() {
    }

    public OrdersRevenueDto(String ct, float revenue) {
        this.ct = ct;
        this.revenue = revenue;
    }
}
