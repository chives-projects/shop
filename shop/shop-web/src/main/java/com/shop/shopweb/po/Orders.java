package com.shop.shopweb.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @description: 订单表
 * @author: csc
 * @create: 2019/12/25 21:27
 */
@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "ordersId", nullable = false, length = 32)
    private String ordersId;

    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
    private User user;

    @Column(name = "actualPayment", length = 8)
    private float actualPayment;

    @Column(name = "createdtime")
    private Timestamp createdtime;

    @Column(name = "flag", length = 1)
    private Integer flag;

//    @OneToMany(targetEntity = OrdersItem.class,mappedBy = "orders")
//    private Set<OrdersItem> ordersItems;

}
