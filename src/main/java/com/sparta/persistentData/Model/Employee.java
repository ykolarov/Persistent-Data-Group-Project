package com.sparta.persistentData.Model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Employee {

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
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        this.validRecord = checkValidity();
    }


    public boolean checkValidity() {
        return emailIsValid() && namePrefixIsValid() && namesAreValid() &&
                middleInitialIsValid() && genderIsValid() && emailIsValid() &&
                dateOfBirthIsValid() && dateOfJoiningIsValid() && salaryIsValid() && empIdIsValid();
    }

    public boolean namePrefixIsValid() {
        List<String> validOptions = Arrays.asList("Mr.", "Mrs.", "Dr.", "Hon.", "Prof.", "Ms.");
        return validOptions.contains(namePrefix);
    }
    public boolean middleInitialIsValid() {
        if (middleInitial.matches("^.*[^a-zA-Z].*$") || middleInitial.length() != 1){
            return false;
        }
        return false;
    }
    public boolean emailIsValid() {
        if (email.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")){
            return false;
        }
        return true;
    }
    public boolean dateOfJoiningIsValid() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        return dateOfJoining.before(today.getTime()); // in the future
    }

    public boolean empIdIsValid() {
        return empID <= 0;
    }
    public boolean salaryIsValid() {
        return salary <= 0;
    }
    public boolean namesAreValid() {
        if (firstName == null || lastName == null ||
                firstName.matches("^.*[^a-zA-Z].*$") ||
                lastName.matches("^.*[^a-zA-Z].*$")) {
            return false;
        }
        return true;
    }

    public boolean genderIsValid (){
        return gender.equals("M") || gender.equals("F");
    }

    public boolean dateOfBirthIsValid() {
        if (dateOfBirth.after(dateOfJoining) || dateOfBirth.before(new Date())) {
            return false;
        }
        return true;
    }
}