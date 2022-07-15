package com.sparta.persistentData.Controller;

import com.sparta.persistentData.Model.CsvReader;
import com.sparta.persistentData.Model.DatabaseManager;
import com.sparta.persistentData.Model.Employee;
import com.sparta.persistentData.View.Displayer;
import com.sparta.persistentData.database.ConnectionManager;
import com.sparta.persistentData.database.RecordDao;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    private final static int NUMBER_OF_THREADS = 1;
    private final static String FILE_NAME = "resources/EmployeeRecordsLarge.csv";

    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader(); // model
        Displayer displayer = new Displayer(); // view
        DatabaseManager databaseManager = new DatabaseManager(); // model

        String result = csvReader.readFile(FILE_NAME);
        displayer.displayData(result);

        databaseManager.start(displayer);
    }
}