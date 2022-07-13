package com.sparta.persistentData.Controller;

import com.sparta.persistentData.Model.CsvReader;
import com.sparta.persistentData.View.Displayer;
import com.sparta.persistentData.objects.Employee;

public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader(); // model
        Displayer displayer = new Displayer(); // view
        String result = csvReader.readFile("resources/EmployeeRecords1.xlsx");
        displayer.displayData(result);
    }
}