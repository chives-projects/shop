package com.shop.shopweb.service.impl;

import com.shop.shopweb.interfaces.facade.dto.OrdersRevenueDto;
import com.shop.shopweb.mapper.OrdersMapper;
import com.shop.shopweb.po.Orders;
import com.shop.shopweb.repo.OrdersRepository;
import com.shop.shopweb.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/27 14:35
 */
@Service
public class OrdersServiceImp implements OrdersService {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders findBykey(String key) {
        Optional<Orders> optionalOrders = ordersRepository.findById(key);
        return optionalOrders.isPresent() ? optionalOrders.get() : null;
    }

    @Override
    public List<Orders> findByDay(String key) {
        return ordersRepository.findByDay(key);
    }

    @Override
    public List<OrdersRevenueDto> findByWeek(String date) {
        return ordersMapper.findByWeek(date);
    }

    @Override
    public boolean deleteById(String id) {
        Orders orders = ordersRepository.findById(id).get();
        orders.setFlag(1);
        ordersRepository.save(orders);
        return true;
    }
}
