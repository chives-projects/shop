package com.shop.shopweb.service.impl;

import com.shop.shopweb.po.Supplier;
import com.shop.shopweb.repo.SupplierRepository;
import com.shop.shopweb.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/25 20:38
 */
@Service
public class SupplierServiceImp implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findBykey(String key) {
        return supplierRepository.findById(key).get();
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        supplier.setCreatedtime(new Timestamp(System.currentTimeMillis()));
        supplierRepository.save(supplier);
        return true;
    }

    @Override
    public boolean deleteById(String id) {
        supplierRepository.deleteById(id);
        return true;
    }
}
