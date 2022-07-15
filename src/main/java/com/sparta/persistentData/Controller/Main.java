package com.sparta.persistentData.Controller;

import com.sparta.persistentData.Model.CsvReader;
import com.sparta.persistentData.database.DatabaseManager;
import com.sparta.persistentData.View.Displayer;

public class Main {

    private final static int NUMBER_OF_THREADS = 1;
    private final static String FILE_NAME = "resources/EmployeeRecordsLarge.csv";

    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader(); // model
        Displayer displayer = new Displayer(); // view
        DatabaseManager databaseManager = new DatabaseManager(NUMBER_OF_THREADS); // model

        String result = csvReader.readFile(FILE_NAME);
        displayer.displayData(result);

        databaseManager.start(displayer);
    }
}