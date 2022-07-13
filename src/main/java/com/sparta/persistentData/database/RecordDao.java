package com.sparta.persistentData.database;

import com.sparta.persistentData.Model.ValidRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

public class RecordDao implements Dao<ValidRecord>{

    private Connection conn;
    private ConnectionManager connectionManager;

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
    public void saveAll(HashSet<ValidRecord> t) {
        PreparedStatement ps;
    }

    @Override
    public void save(ValidRecord validRecord) {

    }

    @Override
    public void update(ValidRecord validRecord, String[] params) {

    }

    @Override
    public void delete(ValidRecord validRecord) {

    }
}
