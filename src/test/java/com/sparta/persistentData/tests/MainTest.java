package com.sparta.persistentData.tests;

import com.sparta.persistentData.Model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Employee employee;
    @BeforeEach
    void setUp() {
        employee = new Employee(647173,"Mr.",
                "Milan","F","Krawczyk","M",
                "milan.krawczyk@hotmail.com",new Date("4/4/1980"),
                new Date("1/19/2012"),123681);
    }

    @Test
    void testNegativeId() {
        employee.setEmpID(-1);
        assertFalse(employee.checkValidity());
    }

    @Test
    void testNegativeSalary() {
        employee.setSalary(-1);
        assertFalse(employee.checkValidity());
    }

    @Test
    void testUnsupportedGender() {
        employee.setGender("G");
        assertFalse(employee.checkValidity());
    }

    @Test
    void testDigitAsFirstName() {
        employee.setFirstName("1000");
        assertFalse(employee.checkValidity());
    }
    @Test
    void testDigitAsLastName() {
        employee.setLastName("999");
        assertFalse(employee.checkValidity());
    }

    @Test
    void testZeroSalary() {
        employee.setSalary(0);
        assertFalse(employee.checkValidity());
    }

    @Test
    void testMiddleInitialTooLong() {
        employee.setMiddleInitial("Johnson");
        assertFalse(employee.checkValidity());
    }

    @Test
    void testMiddleInitialDigit() {
        employee.setMiddleInitial("9");
        assertFalse(employee.checkValidity());
    }

    @Test
    void DobInFuture() {
        Calendar cal = Calendar.getInstance(); // today
        cal.add(Calendar.DATE, 7); // 7 days from today
        employee.setDateOfBirth(cal.getTime()); // in the future
        assertFalse(employee.checkValidity());
    }

    @Test
    void DojInFuture() {
        Calendar cal = Calendar.getInstance(); // today
        cal.add(Calendar.DATE, 7); // 7 days from today
        employee.setDateOfJoining(cal.getTime()); // in the future
        assertFalse(employee.checkValidity());
    }

    @Test
    void DojAfterDob() {
        Calendar cal = Calendar.getInstance(); // today
        employee.setDateOfBirth(cal.getTime()); // today
        cal.add(Calendar.DATE, -100); // 100 days ago
        employee.setDateOfJoining(cal.getTime()); // prior to date of birth
        assertFalse(employee.checkValidity());
    }

    @Test
    void TestValidEmployee() {
        assertTrue(employee.empIdIsValid());
        assertTrue(employee.namePrefixIsValid());
        assertTrue(employee.namesAreValid());
        assertTrue(employee.middleInitialIsValid());
        assertTrue(employee.genderIsValid());
        assertTrue(employee.emailIsValid());
        assertTrue(employee.dateOfBirthIsValid());
        assertTrue(employee.dateOfJoiningIsValid());
        assertTrue(employee.salaryIsValid());
    }

}