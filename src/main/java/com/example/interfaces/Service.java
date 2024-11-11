package com.example.interfaces;

import java.util.List;

public interface Service<T> {
    boolean add();
    boolean remove(T entity);
    boolean edit(T entity);
    boolean print();
    List<T> list();
}
