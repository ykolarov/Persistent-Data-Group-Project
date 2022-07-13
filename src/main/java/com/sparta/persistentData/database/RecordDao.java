package com.sparta.persistentData.database;

import com.sparta.persistentData.Model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

public class RecordDao implements Dao<Employee>{

    private Connection conn;
    private ConnectionManager connectionManager;
    private PreparedStatement preparedInsert;

    private static final String insertStatement =
            "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) VALUES (?,?,?)";


    public RecordDao() {
        connectionManager = new ConnectionManager();
        conn = connectionManager.getDatabaseConnection();

        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll(HashSet<Employee> t) {

    }

    @Override
    public void save(Employee validRecord) {

    }

    @Override
    public void update(Employee validRecord, String[] params) {

    }

    @Override
    public void delete(Employee validRecord) {

    }
}
