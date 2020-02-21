package com.shop.shopweb.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @description: 订单明细
 * @author: csc
 * @create: 2019/12/24 21:15
 */
@Data
@Entity
@Table(name = "ordersItem")
//@IdClass(OrdersPK.class)
public class OrdersItem {
    @EmbeddedId
    private OrdersPK ordersPK;

//    @Id
//    @JoinColumn(name = "ordersId", referencedColumnName = "ordersId")
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Orders orders;
//
//    @Id
//    @JoinColumn(name = "goodsId",referencedColumnName="goodsId")
//    @OneToOne(cascade = CascadeType.ALL)
//    private Goods goods;

    @Column(name = "amount", length = 4)
    private Integer amount;

    //售价
    @Column(name = "sellPrice", length = 8)
    private float sellPrice;

    @Column(name = "createdtime")
    private Timestamp createdtime;

    @Column(name = "flag", length = 1)
    private Integer flag;
}
