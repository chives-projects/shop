package com.shop.shopweb.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @description: 供应商
 * @author: csc
 * @create: 2019/12/25 20:02
 */
@Data
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name = "supplierId", nullable = false, length = 32)
    private String supplierId;

    @Column(name = "supplierName", length = 32)
    private String supplierName;

    @Column(name = "contactsName", length = 32)
    private String contactsName;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "createdtime")
    private Timestamp createdtime;
}
