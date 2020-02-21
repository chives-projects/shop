package com.shop.common.base;

public interface Timestamped<T> {
    T getCreatedTime();

    T getLastModifiedTime();
}