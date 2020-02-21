package com.shop.shopweb.controller;

import com.shop.shopweb.interfaces.facade.dto.PurchaseDto;
import com.shop.shopweb.interfaces.facade.dto.PurchaseReDto;
import com.shop.shopweb.po.Purchase;
import com.shop.shopweb.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/26 22:14
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PurchaseReDto> getAllPurchase() {
        return purchaseService.findAll();
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public Purchase getByKey(@PathVariable String key) {
        return purchaseService.findBykey(key);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean addUser(@RequestBody PurchaseDto purchaseDto) {
        return purchaseService.addPurchase(purchaseDto);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody PurchaseDto purchaseDto) {
        return purchaseService.addPurchase(purchaseDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable String id) {
        return purchaseService.deleteById(id);
    }

}
