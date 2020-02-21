package com.shop.shopweb.repo;

import com.shop.shopweb.po.OrdersItem;
import com.shop.shopweb.po.OrdersPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersItemRepository extends JpaRepository<OrdersItem, OrdersPK> {
    @Query(value = "select * from ordersitem t where t.ordersId=:ordersId", nativeQuery = true)
    public List<OrdersItem> findByOrdersId(@Param("ordersId") String ordersId);
}
