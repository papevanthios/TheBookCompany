package com.bookcompany.builder.repository;

import com.bookcompany.builder.exception.MyException;
import com.bookcompany.builder.model.Entity;

import java.util.ArrayList;
import java.util.List;

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
            if (t.getName() == name)
                return t;
        return null;
    }

    @Override
    public List<T> read() {
        // Deep copy of the list
        List<T> returnList = new ArrayList<>();
        for (T t : tList)
            returnList.add(t);
        return returnList;
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
