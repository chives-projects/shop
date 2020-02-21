package com.shop.shopweb.repo;

import com.shop.shopweb.interfaces.facade.dto.OrdersRevenueDto;
import com.shop.shopweb.po.Orders;
import com.shop.shopweb.po.OrdersPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OrdersRepository extends JpaRepository<Orders, String> {
    @Query(value = "select * from orders where DATE_FORMAT(createdtime,'%Y-%m-%d')=:day",
            nativeQuery = true)
    public List<Orders> findByDay(@Param("day") String day);

    @Query(value = "SELECT DATE_FORMAT(t.createdtime, '%Y-%m-%d') AS ct," +
            "SUM(t.actualPayment) AS revenue FROM orders t WHERE t.createdtime " +
            "BETWEEN DATE_SUB(:day1,INTERVAL 7 DAY) AND :day1" +
            " GROUP BY ct",nativeQuery = true)
    public List<Map<String,Object>> findByWeek(@Param("day1") String day1);
}
