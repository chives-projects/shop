package com.shop.shopweb.service.impl;

import com.shop.shopweb.interfaces.assembler.GoodsAssembler;
import com.shop.shopweb.interfaces.facade.dto.OrdersAddDto;
import com.shop.shopweb.po.*;
import com.shop.shopweb.repo.*;
import com.shop.shopweb.service.SalePersonService;
import com.shop.shopweb.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/24 20:49
 */
@Service
public class SalePersonServiceImp implements SalePersonService {
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrdersItemRepository ordersItemRepository;

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    @Transactional
    public boolean savaOrders(OrdersAddDto ordersAddDto) {
        String orderId = OrderUtil.createOrderNumber(ordersAddDto.getUserId(), 1);
        User user = userRepository.findById(ordersAddDto.getUserId()).get();
        Orders orders = new Orders();
        orders.setOrdersId(orderId);
        orders.setUser(user);
        orders.setActualPayment(ordersAddDto.getActualPayment());
        orders.setCreatedtime(new Timestamp(System.currentTimeMillis()));
        orders.setFlag(0);
        ordersRepository.save(orders);
        List<GoodsAssembler> items = ordersAddDto.getItems();
        for (GoodsAssembler goodsAssembler : items) {
            Goods goods = goodsRepository.findById(goodsAssembler.getGoodsNumber()).get();
            goods.setStockNum(goods.getStockNum() - goodsAssembler.getQuantity());
            OrdersItem ordersItem = new OrdersItem();
            OrdersPK ordersPK = new OrdersPK();
            ordersPK.setOrders(orders);
            ordersPK.setGoods(goods);
//            ordersItem.setOrders(orders);
//            ordersItem.setGoods(goods);
            ordersItem.setOrdersPK(ordersPK);
            ordersItem.setAmount(goodsAssembler.getQuantity());
            ordersItem.setSellPrice(goodsAssembler.getSellPrice());
            ordersItem.setFlag(0);
            System.out.println(ordersItem);
            ordersItemRepository.save(ordersItem);
//            ordersRepository.save(orders);
        }
        return true;
    }
}
