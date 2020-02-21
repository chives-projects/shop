package com.shop.shopweb.service;

import com.shop.shopweb.po.OrdersItem;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/27 16:41
 */
public interface OrdersItemService {
    List<OrdersItem> findAll();

    List<OrdersItem> findBykey(String key);
}
