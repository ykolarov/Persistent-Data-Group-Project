package com.sparta.persistentData.Model;

import java.io.*;
import java.util.HashSet;

public class CsvReader {

    private HashSet<ValidRecord> validRecords;
    private HashSet<ValidRecord> invalidRecords;

    public CsvReader() {
        validRecords = new HashSet<>();
        invalidRecords = new HashSet<>();
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
