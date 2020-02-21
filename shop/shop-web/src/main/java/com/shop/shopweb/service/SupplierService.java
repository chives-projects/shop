package com.shop.shopweb.service;

import com.shop.shopweb.po.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> findAll();

    Supplier findBykey(String key);

    boolean addSupplier(Supplier supplier);

    boolean deleteById(String id);
}
