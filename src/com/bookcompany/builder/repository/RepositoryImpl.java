/**
 * The repository<T> class is for making the repositories of ingredients and recipes in our program.
 * Basic functionalities are: Create, Read(one item), Read(all items), Update and Delete.
 *
 * @author Evanthios Papadopoulos
 * @since 12-Mar-22
 */

package com.bookcompany.builder.repository;

import com.bookcompany.builder.exception.MyException;
import com.bookcompany.builder.model.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositoryImpl<T extends Entity> implements Repository<T> {
    private final List<T> tList = new ArrayList<>();

    @Override
    public boolean create(T t) {
        if (t == null)
            return false;
        tList.add(t);
        return true;
    }

    @Override
    public T read(String name) {
        for (T t : tList)
            if (Objects.equals(t.getName(), name))
                return t;
        return null;
    }

    @Override
    public List<T> read() {
        // Deep copy of the list
        return new ArrayList<>(tList);
    }

    @Override
    public boolean update(String name, String newName) {
        T t = read(name);
        if (t == null)
            return false;
        t.setName(newName);
        return true;
    }

    @Override
    public boolean delete(String name) throws MyException {
        T t = read(name);
        if (t == null)
            throw new MyException("The entity was not found to be deleted.");
        tList.remove(t);
        return true;
    }
}
