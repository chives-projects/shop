package com.shop.shopweb.controller;

import com.shop.shopweb.interfaces.facade.dto.OrdersAddDto;
import com.shop.shopweb.po.Goods;
import com.shop.shopweb.po.Orders;
import com.shop.shopweb.service.SalePersonService;
import com.shop.shopweb.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 销售员
 * @author: csc
 * @create: 2019/12/24 22:59
 */
@RestController
@RequestMapping("/cashier")
public class SalePersonController {
    @Autowired
    SalePersonService salePersonService;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public List<Goods> getAllGoods() {
//        return salePersonService.findAll();
//    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean addOrder(@RequestBody OrdersAddDto ordersAddDto) {
        System.out.println(ordersAddDto);
        return salePersonService.savaOrders(ordersAddDto);
    }
}
