package com.sparta.persistentData.Model;

import com.sparta.persistentData.objects.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class CsvReader {

    private HashSet<ValidRecord> validRecords;
    private ArrayList<InvalidRecord> invalidRecords;

    public CsvReader() {
        validRecords = new HashSet<>();
        invalidRecords = new ArrayList<>();
    }
    public String readFile(String filename) {
        String result = "";
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] recordValues = line.split(",");
                Employee employee = new Employee( Integer.parseInt(recordValues [0]), recordValues [1], recordValues [3], recordValues [4], recordValues [5], recordValues [6], recordValues [7], new Date(recordValues [8]), new Date(recordValues [9]), Integer.parseInt(recordValues [10]));
                boolean valid = employee.checkValidity();
                if (valid){
                    validRecords.add(new ValidRecord(recordValues));
                }
                else invalidRecords.add(new InvalidRecord(recordValues));
            }


        } catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
