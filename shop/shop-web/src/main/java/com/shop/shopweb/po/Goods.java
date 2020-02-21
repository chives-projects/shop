package com.shop.shopweb.po;

import com.shop.common.base.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @description:商品
 * @author: csc
 * @create: 2019/12/24 18:18
 */
@Data
@Entity
@Table(name = "goods")
public class Goods extends BaseEntity {
    @Id
    @Column(name = "goodsId", nullable = false, length = 32)
    private String goodsId;

    @Column(name = "name", length = 32)
    private String name;

    // 进价
    @Column(name = "goodsPrice", length = 8)
    private float goodsPrice;

    //售价
    @Column(name = "sellPrice", length = 8)
    private float sellPrice;

    @Column(name = "unit", length = 10)
    private String unit;

    @Column(name = "stockNum", length = 8)
    private Integer stockNum;

    @Column(name = "alarmNum", length = 4)
    private Integer alarmNum;


//    @Column(name = "kind",length = 4)
//    private int kind;
//
//    @Column(name = "remark",length = 255)
//    private String remark;

}
