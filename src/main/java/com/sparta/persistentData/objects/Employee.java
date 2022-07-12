package com.sparta.persistentData.objects;

import com.sparta.persistentData.enums.Gender;
import com.sparta.persistentData.enums.NamePrefix;
import lombok.Builder;

import java.util.Arrays;
import java.util.Date;

@Builder
public class Employee {

    private final int empID;
    private final NamePrefix namePrefix;
    private final String firstName;
    private final char middleInitial;
    private final String lastName;
    private final Gender gender;
    private final String email;
    private final Date dateOfBirth;
    private final Date dateOfJoining;
    private final int salary;
    private final boolean validRecord;

    public Employee(int empID, NamePrefix namePrefix, String firstName, char middleInitial, String lastName, Gender gender, String email, Date dateOfBirth, Date dateOfJoining, int salary, boolean validRecord) {
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
        this.validRecord = validRecord;


    }

    public int getEmpID() {
        return this.empID;
    }

    public NamePrefix getNamePrefix() {
        return this.namePrefix;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public char getMiddleInitial() {
        return this.middleInitial;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Date getDateOfJoining() {
        return this.dateOfJoining;
    }

    public int getSalary(){return this.salary;}


    public boolean empIdIsValid() {
        if ((empID <= 0)){
            return false;
        }
        return true;
    }
    public boolean salaryIsValid() {
        if (salary <= 0) {
            return false;
        }
        return true;
    }
    public boolean namesAreValid() {
        if (firstName == null || lastName == null) {
            return false;
        }
        return true;
    }
    public





    public boolean dateOfBirthIsValid() {
        if (dateOfBirth.after(dateOfJoining) || dateOfBirth.before(new Date())) {
            return false;
        }
        return true;
    }

}