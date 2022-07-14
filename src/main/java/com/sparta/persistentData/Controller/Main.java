package com.sparta.persistentData.Controller;

import com.sparta.persistentData.Model.CsvReader;
import com.sparta.persistentData.View.Displayer;

public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader(); // model
        Displayer displayer = new Displayer(); // view
        String result = csvReader.readFile("resources/CustomEmployee.csv");
        displayer.displayData(result);
    }
}