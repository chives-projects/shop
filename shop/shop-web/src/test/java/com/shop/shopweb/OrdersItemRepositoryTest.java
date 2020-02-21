package com.shop.shopweb;

import com.shop.shopweb.interfaces.facade.dto.OrdersRevenueDto;
import com.shop.shopweb.mapper.OrdersMapper;
import com.shop.shopweb.po.Orders;
import com.shop.shopweb.po.OrdersItem;
import com.shop.shopweb.repo.OrdersItemRepository;
import com.shop.shopweb.repo.OrdersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/27 16:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersItemRepositoryTest {
    Logger logger = LoggerFactory.getLogger(OrdersItemRepositoryTest.class);

    @Autowired
    OrdersItemRepository ordersItemRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrdersMapper ordersMapper;

    @Test
    public void findall(){
        ordersItemRepository.findAll();
    }

    @Test
    public void add(){
//        OrdersItem ordersItem=new OrdersItem();

//        List<OrdersItem> list=ordersItemRepository.findByOrdersId("12311912276809459125539478702292");
//        for (OrdersItem ordersItem:list){
//            System.out.println(ordersItem);
//        }
//        List<Orders> list1=ordersRepository.findByDay("2019-12-27");
//        for (Orders orders:list1){
//            System.out.println(orders);
//        }

//        List<Map<String,Object>> list2=ordersRepository.findByWeek("2020-01-01");

        List<OrdersRevenueDto> list=ordersMapper.findByWeek("2020-01-01");
        for (OrdersRevenueDto ordersRevenueDto:list){
            System.out.println(ordersRevenueDto);
        }
//        Optional<OrdersItem> optionalOrders = ordersItemRepository.findById("123");
//        return optionalOrders.isPresent() ? optionalOrders.get() : null;

    }

    @Test
    public void findByWeek(){
        List<Map<String,Object>> list2=ordersRepository.findByWeek("2020-01-01");
        System.out.println(list2.size());

        for (Map<String,Object> map:list2){
            System.out.println("mapsize:"+map.size());
            for (Map.Entry entry:map.entrySet()){

                System.out.print("key:"+entry.getKey());
                System.out.println("  value:"+entry.getValue().getClass());
            }
            System.out.println("map end");
        }
    }

}
