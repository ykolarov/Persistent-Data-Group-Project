package com.sparta.persistentData.database;

import com.sparta.persistentData.Model.Employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

public class RecordDao implements Dao<Employee>{

    private Connection conn;
    private ConnectionManager connectionManager;
    private PreparedStatement preparedInsert;

    private static final String INSERT_STATEMENT =
            "INSERT INTO EMPLOYEE " +
            "(ID, NAME_PREFIX, FIRST_NAME, MIDDLE_INITIAL," +
            " LAST_NAME, GENDER, EMAIL, DATE_OF_BIRTH, DATE_OF_JOINING, SALARY)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?)";

    public RecordDao() {
        try {
            connectionManager = new ConnectionManager();
            conn = connectionManager.getDatabaseConnection();
            preparedInsert = conn.prepareStatement(INSERT_STATEMENT);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll(HashSet<Employee> toBeInserted) {
        try {
            for (Employee e : toBeInserted) {
                preparedInsert.setInt(1, e.getEmpID());
                preparedInsert.setString(2, e.getNamePrefix());
                preparedInsert.setString(3, e.getFirstName());
                preparedInsert.setString(4, e.getMiddleInitial());
                preparedInsert.setString(5, e.getLastName());
                preparedInsert.setString(6, e.getGender());
                preparedInsert.setString(7, e.getEmail());
                preparedInsert.setDate(8, (Date) e.getDateOfBirth());
                preparedInsert.setDate(9, (Date) e.getDateOfJoining());
                preparedInsert.setInt(10, e.getSalary());
                int result = preparedInsert.executeUpdate();
                System.out.println("rows updated: " + result);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
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
