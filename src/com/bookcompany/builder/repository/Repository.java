package com.bookcompany.builder.repository;

import com.bookcompany.builder.exception.MyException;

import java.util.List;

public interface Repository<T> {
    // CRUD
    boolean create(T t);
    T read(String name);
    List<T> read();
    boolean update(String name, String newName);
    boolean delete(String name) throws MyException;
}
