package com.shop.common.base;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * @author csc
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    // 删除标识
    @Column(name = "isDelete", columnDefinition = "bit default 0")
    private Boolean isDelete = false;

    @Column(name = "createdtime")
    @CreationTimestamp
    private Timestamp createdtime;

    @Column(name = "updateTime")
    @UpdateTimestamp
    private Timestamp updateTime;

    public @interface Update {}

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }
}
