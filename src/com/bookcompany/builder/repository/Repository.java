/**
 * The repository<T> interface is for making the repositories of ingredients and recipes in our program.
 * Basic functionalities are: Create, Read(one item), Read(all items), Update and Delete.
 *
 * @author Evanthios Papadopoulos
 * @since 12-Mar-22
 */

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
