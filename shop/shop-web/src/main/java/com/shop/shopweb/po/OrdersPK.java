package com.shop.shopweb.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/25 0:44
 */
@Data
@Embeddable
public class OrdersPK implements Serializable {
    @JoinColumn(name = "ordersId", referencedColumnName = "ordersId")
    @ManyToOne(cascade = CascadeType.ALL)
    private Orders orders;
    //    private String goodsId;

    @JoinColumn(name = "goodsId", referencedColumnName = "goodsId")
    @OneToOne(cascade = CascadeType.ALL)
    private Goods goods;

    /**
     * 无参数的public构造方法，必须要有
     */
    public OrdersPK() {
    }

    public OrdersPK(Orders orders, Goods goods) {
        this.orders = orders;
        this.goods = goods;
    }

    @Override
    public int hashCode() {
        return this.orders.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof OrdersPK)) return false;
        OrdersPK ordersId = (OrdersPK) o;
        if (ordersId.equals(ordersId.orders) && goods.equals(ordersId.goods)) return true;
        return false;
    }
}


