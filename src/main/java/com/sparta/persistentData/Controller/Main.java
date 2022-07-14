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
        String result = csvReader.readFile("resources/CustomEmployee.csv");
        displayer.displayData(result);

        insertEmployeesInDatabase();
    }

    private static void insertEmployeesInDatabase() {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection conn = connectionManager.getDatabaseConnection();
        RecordDao recordDao = new RecordDao(conn);
        recordDao.saveAll(Employee.getValidRecords());
        connectionManager.closeDatabaseConnection();
    }
}