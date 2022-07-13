package com.sparta.persistentData.objects;

import lombok.Builder;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Employee {

    private final int empID;
    private final String namePrefix;
    private final String firstName;
    private final String middleInitial;
    private final String lastName;
    private final String gender;
    private final String email;
    private final Date dateOfBirth;
    private final Date dateOfJoining;
    private final int salary;
    private final boolean validRecord;

    public Employee(@NonNull int empID,
                    @NonNull String namePrefix,
                    @NonNull String firstName,
                    @NonNull String middleInitial,
                    @NonNull String lastName,
                    @NonNull String gender,
                    @NonNull String email,
                    @NonNull Date dateOfBirth,
                    @NonNull Date dateOfJoining,
                    @NonNull int salary,
                    @NonNull boolean validRecord
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
        return emailIsValid() && namePrefixIsValid() && namesAreValid() && middleInitialIsValid() && genderIsValid() && emailIsValid()
                && emailIsValid() && dateOfBirthIsValid() && dateOfJoiningIsValid() && salaryIsValid();
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
        return dateOfBirth.before(new Date()); // in the future
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
        if (gender.length() != 1 || !gender.equals("M") || !gender.equals("F")){
            return false;
        }
        return true;
    }

    public boolean dateOfBirthIsValid() {
        if (dateOfBirth.after(dateOfJoining) || dateOfBirth.before(new Date())) {
            return false;
        }
        return true;
    }



    public int getEmpID() {
        return this.empID;
    }

    public String getNamePrefix() {
        return this.namePrefix;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleInitial() {
        return this.middleInitial;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getGender() {
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
}