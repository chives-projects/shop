package com.shop.shopweb.service;


import com.shop.shopweb.interfaces.facade.dto.OrdersAddDto;
import com.shop.shopweb.po.Goods;

import java.util.List;

public interface SalePersonService {
    public List<Goods> findAll();

    public boolean savaOrders(OrdersAddDto ordersAddDto);
}
