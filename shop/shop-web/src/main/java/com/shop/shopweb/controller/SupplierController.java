package com.shop.shopweb.controller;

import com.shop.shopweb.po.Supplier;
import com.shop.shopweb.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/25 20:26
 */
@RequestMapping("/supplier")
@RestController
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Supplier> getAllUser() {
        return supplierService.findAll();
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public Supplier getByKey(@PathVariable String key) {
        return supplierService.findBykey(key);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean addUser(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable String id) {
        return supplierService.deleteById(id);
    }

}
