package com.shop.shopweb.service;

import com.shop.shopweb.po.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> findAll();

    Goods findBykey(String key);

    boolean addGoods(Goods goods);

    boolean deleteById(String id);
}
