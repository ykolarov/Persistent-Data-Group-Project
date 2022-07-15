package com.sparta.persistentData.Model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

//import java.sql.Date;
import java.util.*;


@Getter
@Setter
public class Employee {

    private static HashSet<Employee> validRecords = new HashSet<>();
    private static HashSet<Integer> usedEmployeeIds = new HashSet<>();
    private static ArrayList<Employee> invalidRecords = new ArrayList<>();
    private static int IDDupCount = 0;
    private static int missingCount = 0;

    public static int getMissingCount(){
        return missingCount;
    }

    public static int getIDDupCount() {
        return IDDupCount;
    }
    public static HashSet<Employee> getValidRecords() {
        return validRecords;
    }

    public static ArrayList<Employee> getInvalidRecords() {
        return invalidRecords;
    }

    private  int empID;
    private  String namePrefix;
    private  String firstName;
    private  String middleInitial;
    private  String lastName;
    private  String gender;
    private  String email;
    private  Date dateOfBirth;
    private  Date dateOfJoining;
    private  int salary;
    private  boolean validRecord;

    public Employee(@NonNull int empID,
                    @NonNull String namePrefix,
                    @NonNull String firstName,
                    @NonNull String middleInitial,
                    @NonNull String lastName,
                    @NonNull String gender,
                    @NonNull String email,
                    @NonNull Date dateOfBirth,
                    @NonNull Date dateOfJoining,
                    @NonNull int salary
    ) {
        this.empID = empID;
        if (empID == 0){
            missingCount +=1;
        }
        this.namePrefix = namePrefix;
        if (namePrefix == null){
            missingCount +=1;
        }
        this.firstName = firstName;
        if (firstName == null){
            missingCount +=1;
        }
        this.middleInitial = middleInitial;
        if (middleInitial == null){
            missingCount +=1;
        }
        this.lastName = lastName;
        if (lastName == null){
            missingCount +=1;
        }
        this.gender = gender;
        if (gender == null){
            missingCount +=1;
        }
        this.email = email;
        if (email == null){
            missingCount +=1;
        }
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        if (salary == 0){
            missingCount +=1;
        }
        if(usedEmployeeIds.contains(empID)) {
            this.validRecord = false;
            IDDupCount +=1;
        } else {
            usedEmployeeIds.add(empID);
            this.validRecord = checkValidity();
        }

    }

    public boolean checkValidity() {
        return emailIsValid() && namePrefixIsValid() && namesAreValid() &&
                middleInitialIsValid() && genderIsValid() && emailIsValid() &&
                dateOfBirthIsValid() && dateOfJoiningIsValid() && salaryIsValid()
                && empIdIsValid() ;
    }

    public boolean namePrefixIsValid() {
        List<String> validOptions = Arrays.asList("Mr.", "Mrs.", "Drs.", "Hon.", "Prof.", "Ms.", "Dr.");
        return validOptions.contains(namePrefix);
    }

    public boolean middleInitialIsValid() {
        return middleInitial.matches("^[A-Z]$") && middleInitial.length() == 1;
    }

    public boolean emailIsValid() {
        return email.matches("^[a-zA-Z0-9_#$%&’*+/=?^.-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }
    public boolean dateOfJoiningIsValid() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        return dateOfJoining.before(today.getTime()); // in the future
    }

    @Override
    public String toString() {
        return this.namePrefix + " " + this.firstName + " " +
                this.middleInitial + " " + this.lastName + " with email " + this.email
                 + " and gender " + this.gender +
                " born in " + this.dateOfBirth + " joined in " + this.dateOfJoining +
                " with employee id " + this.empID + " and salary " + this.salary;
    }

    public boolean empIdIsValid() {
        return empID >= 0;
    }
    public boolean salaryIsValid() {
        return salary > 0;
    }
    public boolean namesAreValid() {
        return firstName != null && lastName != null &&
                firstName.matches("^[a-zA-z]+$") &&
                lastName.matches("^[a-zA-z]+$");
    }

    public boolean genderIsValid (){
        return gender.equals("M") || gender.equals("F");
    }

    public boolean dateOfBirthIsValid() {
        return !dateOfBirth.after(dateOfJoining)
                && !dateOfBirth.after(new Date());
    }
}