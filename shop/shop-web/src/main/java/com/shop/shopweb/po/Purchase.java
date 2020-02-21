package com.shop.shopweb.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @description: 进货
 * @author: csc
 * @create: 2019/12/25 19:32
 */
@Data
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @OneToOne
    private User user;

    @JoinColumn(name = "goodsId", referencedColumnName = "goodsId")
    @OneToOne(cascade = CascadeType.ALL)
    private Goods goods;

    @JoinColumn(name = "supplierId", referencedColumnName = "supplierId")
    @OneToOne(cascade = CascadeType.ALL)
    private Supplier supplier;


    @Column(name = "goodsPrice", length = 8)
    private float goodsPrice;

    @Column(name = "sellPrice", length = 8)
    private float sellPrice;

    @Column(name = "num", length = 4)
    private Integer num;

    @Column(name = "createdtime")
    private Timestamp createdtime;

    @Column(name = "flag", length = 1)
    private Integer flag;
}
