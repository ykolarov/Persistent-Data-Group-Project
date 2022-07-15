package com.sparta.persistentData.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public class CsvReader {

    public String readFile(String filename){
        String result = "Data for file: " + filename;
        var start = System.currentTimeMillis();
        File file = new File(filename);
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream
                    .skip(1) // skip header
                    .forEach(line -> addNewEmployee(line.split(",")));
        }catch (IOException e) {
            e.printStackTrace();
        }
        var stop = System.currentTimeMillis();
        System.out.println("streams took: " + (stop-start) + " ms to read all lines");

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
        return result;
    }

    private static void addNewEmployee(String[] recordValues){
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
