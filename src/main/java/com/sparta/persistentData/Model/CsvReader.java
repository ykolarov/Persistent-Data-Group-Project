package com.sparta.persistentData.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Stream;

public class CsvReader {

    public String readFile(String filename) {
        String result = "Data for file: " + filename;

//        try {
//            Stream<String> lines = Files.lines(Path.of("C:\\Users\\35987\\Desktop\\B&B\\PersistentData\\resources\\EmployeeRecords1.csv"));
//
//            lines.forEach(str -> Arrays.stream(str.split(",")).toArray());
//            addNewEmployee((String[]) lines.);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

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
        result += "Amount of valid(unique and clean) records: " + Employee.getValidRecords().size();
        result += "\nAmount of invalid records: " + Employee.getInvalidRecords().size();
        result += "\nAmount of Employee ID duplicates: " + Employee.getIDDupCount();
        result += "\nAmount of empty or null fields: " + Employee.getMissingCount();
        if(Employee.getInvalidRecords().size() > 3) {
            result += "\n3 examples of invalid records: ";
            for (int i = 0; i < 3; i++) {
                result += "\n" + Employee.getInvalidRecords().get(i).toString();
            }
        }



        return "result";
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
        if (employee.isValidRecord())
            Employee.getValidRecords().add(employee);
        else Employee.getInvalidRecords().add(employee);
    }
}
