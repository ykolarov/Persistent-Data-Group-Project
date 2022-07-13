package com.sparta.persistentData.Model;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    T get(int empID);

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
