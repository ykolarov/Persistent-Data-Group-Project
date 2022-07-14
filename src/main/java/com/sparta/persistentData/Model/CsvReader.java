package com.sparta.persistentData.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class CsvReader {

    public String readFile(String filename) {
        String result = "";
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
             String line;
             bufferedReader.readLine();
             while ((line = bufferedReader.readLine()) != null) {
                 String[] recordValues = line.split(",");
                 addNewEmployee(recordValues);
             }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void addNewEmployee(String[] recordValues){
        Employee employee = new Employee(
                Integer.parseInt(recordValues [0]),
                recordValues [1],
                recordValues [2],
                recordValues [3],
                recordValues [4],
                recordValues [5],
                recordValues [6],
                new Date(recordValues [7]),
                new Date(recordValues [8]),
                Integer.parseInt(recordValues [9])
        );
        if (employee.checkValidity())
            Employee.getValidRecords().add(employee);
        else Employee.getInvalidRecords().add(employee);
    }
}
