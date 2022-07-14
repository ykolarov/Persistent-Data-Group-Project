package com.sparta.persistentData.Controller;

import com.sparta.persistentData.Model.CsvReader;
import com.sparta.persistentData.Model.Employee;
import com.sparta.persistentData.View.Displayer;
import com.sparta.persistentData.database.ConnectionManager;
import com.sparta.persistentData.database.RecordDao;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader(); // model
        Displayer displayer = new Displayer(); // view
        ConnectionManager connectionManager = new ConnectionManager();
        Connection conn = connectionManager.getDatabaseConnection();
        RecordDao recordDao = new RecordDao(conn);

        String result = csvReader.readFile("resources/EmployeeRecords1.csv");
        displayer.displayData(result);

        insertRecordsInDb(recordDao);
        allowUserToQueryDb(displayer, recordDao);

        connectionManager.closeDatabaseConnection();
    }

    private static void allowUserToQueryDb(Displayer displayer, RecordDao recordDao) {
        while(true) {
            int empId = displayer.getKeyboardInput();
            if (empId == -1) break;
            Employee e = recordDao.get(empId);
            displayer.displayData(e.toString());
        }
    }

    private static void insertRecordsInDb(RecordDao recordDao) {
        var start = System.currentTimeMillis();
        recordDao.createTable("Employee");
        recordDao.saveAll(Employee.getValidRecords());
        var stop = System.currentTimeMillis();
        System.out.println("Time to insert values in DB: " + (stop-start)/1000 + "sec");
    }
}