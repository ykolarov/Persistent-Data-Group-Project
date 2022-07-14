package com.sparta.persistentData.database;

import java.util.HashSet;

public interface Dao<T> {
    void saveAll(HashSet<T> t);

    T get(int empId);

}
