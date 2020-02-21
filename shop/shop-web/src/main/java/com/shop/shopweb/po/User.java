package com.shop.shopweb.po;

/**
 * @description:
 * @author: csc
 * @create: 2019/12/16 1:38
 */

import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "user")
public class User {
    @Id
    @Column(name = "userId", nullable = false, length = 32)
    private String userId;

//    @Column(name = "account", length = 32)
//    private String account;

    @Column(name = "password", length = 32)
    private String password;

    @Column(name = "role", length = 1)
    private Integer role;

    @Column(name = "name", length = 32)
    private String name;

    @Column(name = "sex", length = 1)
    private Integer sex;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "flag", length = 1)
    private Integer flag;


    public User() {
    }

//    public User(String userId, String account) {
//        this.userId = userId;
//        this.account = account;
//    }
}
