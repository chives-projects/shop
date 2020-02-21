package com.shop.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author csc
 */
@Getter
@Setter
public class BaseDTO  implements Serializable {

    private Boolean isDelete;

    private Timestamp createTime;

    private Timestamp updateTime;
}
