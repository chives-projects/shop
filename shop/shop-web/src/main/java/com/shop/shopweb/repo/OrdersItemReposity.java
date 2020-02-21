package com.shop.shopweb.repo;

import com.shop.shopweb.po.OrdersItem;
import com.shop.shopweb.po.OrdersPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersItemReposity extends JpaRepository<OrdersItem, OrdersPK> {
}
