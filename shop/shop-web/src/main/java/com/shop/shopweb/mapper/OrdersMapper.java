package com.shop.shopweb.mapper;

import com.shop.shopweb.interfaces.facade.dto.OrdersRevenueDto;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersMapper {
    public List<OrdersRevenueDto> findByWeek(@Param("key")String key);
}
