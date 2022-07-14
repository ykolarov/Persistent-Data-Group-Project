package com.sparta.persistentData.Model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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

    public boolean empIdIsValid() {
        return empID >= 0;
    }
    public boolean salaryIsValid() {
        return salary <= 0;
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
        return !dateOfBirth.after(dateOfJoining) && !dateOfBirth.after(new Date());
    }
}