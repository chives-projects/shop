package com.shop.shopweb.service;

import com.shop.shopweb.interfaces.facade.dto.OrdersRevenueDto;
import com.shop.shopweb.po.Orders;

import java.util.List;
import java.util.Map;

public interface OrdersService {
    List<Orders> findAll();

    Orders findBykey(String key);

    List<Orders> findByDay(String key);

    List<OrdersRevenueDto> findByWeek(String date);

    boolean deleteById(String id);
}
