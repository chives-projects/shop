package com.shop.shopweb.controller;

import com.shop.shopweb.po.OrdersItem;
import com.shop.shopweb.service.OrdersItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/27 16:40
 */
@RestController
@RequestMapping("/ordersItem")
public class OrdersItemController {
    @Autowired
    OrdersItemService ordersItemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<OrdersItem> getAllUser() {
        return ordersItemService.findAll();
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public List<OrdersItem> getByKey(@PathVariable String key) {
        return ordersItemService.findBykey(key);
    }
}
