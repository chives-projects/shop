package com.shop.shopweb.service;

import com.shop.shopweb.interfaces.facade.dto.PurchaseDto;
import com.shop.shopweb.interfaces.facade.dto.PurchaseReDto;
import com.shop.shopweb.po.Purchase;

import java.util.List;

public interface PurchaseService {
    List<PurchaseReDto> findAll();

    Purchase findBykey(String key);

    boolean addPurchase(PurchaseDto purchaseDto);

    boolean deleteById(String id);
}
