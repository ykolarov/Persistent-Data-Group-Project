package com.sparta.persistentData.View;

import java.io.IOException;
import java.util.Scanner;

public class Displayer {
    private Scanner scanner;
    public Displayer() {
         scanner = new Scanner(System.in);
    }
    public void displayData(String data){
        System.out.println(data);
    }

    public int getKeyboardInput() {
        try {
            System.out.println("Please enter employeeID to query DB: (-1:exit)");
            int res = scanner.nextInt();
            scanner.nextLine();
            return res;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
