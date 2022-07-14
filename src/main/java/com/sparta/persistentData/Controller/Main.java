package com.sparta.persistentData.Controller;

import com.sparta.persistentData.Model.CsvReader;
import com.sparta.persistentData.Model.Employee;
import com.sparta.persistentData.View.Displayer;
import com.sparta.persistentData.database.ConnectionManager;
import com.sparta.persistentData.database.RecordDao;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader(); // model
        Displayer displayer = new Displayer(); // view
        String result = csvReader.readFile("resources/EmployeeRecords1.csv");
        displayer.displayData(result);
        var start = System.currentTimeMillis();
        insertEmployeesInDatabase();
        var stop = System.currentTimeMillis();
        System.out.println("Time to complete process: " + (stop-start) + "ms");
    }

    private static void insertEmployeesInDatabase() {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection conn = connectionManager.getDatabaseConnection();
        RecordDao recordDao = new RecordDao(conn);
        recordDao.createTable("Employee");
        recordDao.saveAll(Employee.getValidRecords());
        connectionManager.closeDatabaseConnection();
    }
}