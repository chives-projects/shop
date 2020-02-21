package com.shop.shopweb.controller;

import com.shop.shopweb.interfaces.facade.dto.OrdersRevenueDto;
import com.shop.shopweb.po.Orders;
import com.shop.shopweb.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/27 17:33
 */
@RestController
@RequestMapping("/revenue")
public class RevenueController {
    @Autowired
    OrdersService ordersService;

    /*
    查询当天之前七天的订单记录
     */
    @RequestMapping(value = "/week/{date}", method = RequestMethod.GET)
    public List<OrdersRevenueDto> getWeek(@PathVariable String date) {
        return ordersService.findByWeek(date);
    }
}
