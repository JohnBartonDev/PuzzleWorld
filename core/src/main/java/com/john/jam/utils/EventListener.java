package com.john.jam.utils;

public interface EventListener<T> {
    void update(int type, T t);
}
