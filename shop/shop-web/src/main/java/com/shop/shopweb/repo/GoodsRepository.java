package com.shop.shopweb.repo;

import com.shop.shopweb.po.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, String> {
}
