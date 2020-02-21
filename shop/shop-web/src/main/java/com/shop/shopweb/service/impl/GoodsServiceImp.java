package com.shop.shopweb.service.impl;

import com.shop.shopweb.po.Goods;
import com.shop.shopweb.repo.GoodsRepository;
import com.shop.shopweb.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/25 20:31
 */
@Service
public class GoodsServiceImp implements GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods findBykey(String key) {
        return goodsRepository.findById(key).get();
    }

    @Override
    public boolean addGoods(Goods goods) {
        goodsRepository.save(goods);
        return true;
    }

    @Override
    public boolean deleteById(String id) {
        goodsRepository.deleteById(id);
        return true;
    }
}
