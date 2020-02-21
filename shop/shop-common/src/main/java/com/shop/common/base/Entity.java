package com.shop.common.base;

import java.io.Serializable;

public interface Entity<S, ID> extends Serializable {
    ID identity();

    boolean sameIdentityAs(S other);

    String describe();
}
