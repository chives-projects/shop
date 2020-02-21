package com.shop.shopweb.controller;

import com.shop.shopweb.po.Goods;
import com.shop.shopweb.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/25 20:26
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Goods> getAllUser() {
        return goodsService.findAll();
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public Goods getByKey(@PathVariable String key) {
        return goodsService.findBykey(key);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean addUser(@RequestBody Goods goods) {
        return goodsService.addGoods(goods);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody Goods goods) {
        return goodsService.addGoods(goods);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable String id) {
        return goodsService.deleteById(id);
    }
}
