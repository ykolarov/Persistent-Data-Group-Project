package com.sparta.persistentData.View;

public class Displayer {
    private Scanner scanner;
    public Displayer() {
         scanner = new Scanner(System.in);
    }
    public void displayData(String data){
        System.out.println(data);
    }
}
