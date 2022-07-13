package com.sparta.persistentData.database;

import java.util.HashSet;
import java.util.List;

public interface Dao<T> {
    void saveAll(HashSet<T> t);

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
