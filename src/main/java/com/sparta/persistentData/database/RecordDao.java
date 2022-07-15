package com.sparta.persistentData.database;

import com.sparta.persistentData.Model.Employee;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;

public class RecordDao implements Dao<Employee>{

    private final Connection conn;
    private static final String INSERT_STATEMENT =
            "INSERT INTO EMPLOYEE " +
            "(empID, NAME_PREFIX, FIRST_NAME, MIDDLE_INITIAL," +
            " LAST_NAME, GENDER, EMAIL, DATE_OF_BIRTH, DATE_OF_JOINING, SALARY)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?)";

    public RecordDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void saveAll(HashSet<Employee> toBeInserted) {
        try (PreparedStatement preparedInsert = conn.prepareStatement(INSERT_STATEMENT)) {
            for (Employee e : toBeInserted) {
                preparedInsert.setInt(1, e.getEmpID());
                preparedInsert.setString(2, e.getNamePrefix());
                preparedInsert.setString(3, e.getFirstName());
                preparedInsert.setString(4, e.getMiddleInitial());
                preparedInsert.setString(5, e.getLastName());
                preparedInsert.setString(6, e.getGender());
                preparedInsert.setString(7, e.getEmail());
                preparedInsert.setDate(8,  new java.sql.Date(e.getDateOfBirth().getTime()));
                preparedInsert.setDate(9,  new java.sql.Date(e.getDateOfJoining().getTime()));
                preparedInsert.setInt(10, e.getSalary());
                preparedInsert.addBatch();
            }
            int[] updatedCount = preparedInsert.executeBatch();
            System.out.println("rows inserted: " + Arrays.stream(updatedCount).sum()
                + " by " + Thread.currentThread().getName());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createTable(String name){
        dropTableIfExists(name);
        String createSql = "CREATE TABLE " + name +
                "(empID INTEGER not NULL, " +
                " NAME_PREFIX VARCHAR(10), " +
                " FIRST_NAME VARCHAR(40), " +
                " MIDDLE_INITIAL VARCHAR(1), " +
                " LAST_NAME VARCHAR(40), " +
                " GENDER VARCHAR(1), " +
                " EMAIL VARCHAR(100), " +
                " DATE_OF_BIRTH DATE, " +
                " DATE_OF_JOINING DATE, " +
                " SALARY INTEGER, " +
                " PRIMARY KEY ( empID ))";
        try (Statement createStatement = conn.createStatement()) {
            createStatement.executeUpdate(createSql);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTableIfExists(String name) {
        String dropSql = "DROP TABLE IF EXISTS " + name;
        try (Statement dropStatement = conn.createStatement()) {
            dropStatement.executeUpdate(dropSql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public Employee get(int empID) {
        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from employee where empID = " + empID);
            if(rs.next()) {
                return new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getDate(9),
                        rs.getInt(10));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
