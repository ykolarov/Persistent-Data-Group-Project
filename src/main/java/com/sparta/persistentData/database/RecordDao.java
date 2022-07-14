package com.sparta.persistentData.database;

import com.sparta.persistentData.Model.Employee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

public class RecordDao implements Dao<Employee>{

    // TODO: write sql statement to retrieve individual records ->
    //      get method below + get user keyboard input in displayer to call the function

    // TODO: if table exists drop first ->
    //      then insert all into table

    private final PreparedStatement preparedInsert;

    private static final String INSERT_STATEMENT =
            "INSERT INTO EMPLOYEE " +
            "(ID, NAME_PREFIX, FIRST_NAME, MIDDLE_INITIAL," +
            " LAST_NAME, GENDER, EMAIL, DATE_OF_BIRTH, DATE_OF_JOINING, SALARY)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?)";

    public RecordDao(Connection conn) {
        try {
            preparedInsert = conn.prepareStatement(INSERT_STATEMENT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll(HashSet<Employee> toBeInserted) {
        int updatedCount = 0 ;
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
                updatedCount += preparedInsert.executeUpdate();
            }
            System.out.println("rows updated: " + updatedCount);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Employee get(int empId) {
        return null;
    }

}
