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
                // TODO: check if valid and if so make new ValidRecord Object, else make new InvalidRecord Object
                // TODO: if(valid) validRecords.add(new ValidRecord(recordValues));
                // TODO: else invalidRecords.add(new InvalidRecord(recordValues));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
