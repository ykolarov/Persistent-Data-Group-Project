package com.sparta.persistentData.Model;

import java.io.*;

public class CsvReader {

    public String readFile(String filename) {
        String result = "";
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] recordValues = line.split(",");
                // TODO: check if valid and if so make new ValidRecord Object, else make new InvalidRecord Object
                // TODO: use builder design pattern
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
