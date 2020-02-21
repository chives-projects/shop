package com.shop.shopweb.service.impl;

import com.shop.shopweb.interfaces.facade.dto.PurchaseDto;
import com.shop.shopweb.interfaces.facade.dto.PurchaseReDto;
import com.shop.shopweb.po.Goods;
import com.shop.shopweb.po.Purchase;
import com.shop.shopweb.po.Supplier;
import com.shop.shopweb.po.User;
import com.shop.shopweb.repo.GoodsRepository;
import com.shop.shopweb.repo.PurchaseRepository;
import com.shop.shopweb.repo.SupplierRepository;
import com.shop.shopweb.repo.UserRepository;
import com.shop.shopweb.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/26 22:40
 */
@Service
public class PurchaseServiceImp implements PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<PurchaseReDto> findAll() {
        List<Purchase> list = purchaseRepository.findAll();
        List<PurchaseReDto> res = new ArrayList<>();
        for (Purchase purchase : list) {
            PurchaseReDto purchaseReDto = new PurchaseReDto();
            purchaseReDto.setGoodsId(purchase.getGoods().getGoodsId());
            purchaseReDto.setSupplierId(purchase.getSupplier().getSupplierId());
            purchaseReDto.setGoodsPrice(purchase.getGoodsPrice());
            purchaseReDto.setNum(purchase.getNum());
            purchaseReDto.setUserId(purchase.getUser().getUserId());
            res.add(purchaseReDto);
        }
        return res;
//        return purchaseRepository.findAll();
    }

    @Override
    public Purchase findBykey(String key) {
        return purchaseRepository.findById(key).get();
    }

    @Override
    public boolean addPurchase(PurchaseDto purchaseDto) {
        Purchase purchase = new Purchase();
        purchase.setCreatedtime(new Timestamp(System.currentTimeMillis()));
        Goods goods = goodsRepository.findById(purchaseDto.getGoodsId()).get();
        goods.setStockNum(goods.getStockNum() + purchaseDto.getNum());
        purchase.setGoods(goods);
        Supplier supplier = supplierRepository.findById(purchaseDto.getSupplierId()).get();
        purchase.setSupplier(supplier);
        purchase.setGoodsPrice(purchaseDto.getGoodsPrice());
        purchase.setSellPrice(purchaseDto.getSellPrice());
        purchase.setNum(purchaseDto.getNum());
        User user = userRepository.findById(purchaseDto.getUserId()).get();
        purchase.setUser(user);
        if (purchaseDto.getId() != null || "".equals(purchaseDto.getId()))
            purchase.setId(purchaseDto.getId());
        purchaseRepository.save(purchase);
        return true;
    }

    @Override
    public boolean deleteById(String id) {
        purchaseRepository.deleteById(id);
        return true;
    }
}
