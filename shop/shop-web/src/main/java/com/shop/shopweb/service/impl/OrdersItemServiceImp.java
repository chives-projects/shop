package com.shop.shopweb.service.impl;

import com.shop.shopweb.po.Orders;
import com.shop.shopweb.po.OrdersItem;
import com.shop.shopweb.repo.OrdersItemRepository;
import com.shop.shopweb.service.OrdersItemService;
import com.shop.shopweb.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/27 16:41
 */
@Service
public class OrdersItemServiceImp implements OrdersItemService {
    @Autowired
    OrdersItemRepository ordersItemRepository;
    @Override
    public List<OrdersItem> findAll() {
        return ordersItemRepository.findAll();
    }

    @Override
    public List<OrdersItem> findBykey(String ordersId) {

        return ordersItemRepository.findByOrdersId(ordersId);
    }
}
