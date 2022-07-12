package com.sparta.persistentData.Controller;

import org.junit.jupiter.api.Test;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testNegativeId() {
        Employee invalid = new Employee();
        invalid.setId(-1);
        assertFalse(invalid.checkValidRecord());
    }

    @Test
    void testNegativeSalary() {
        Employee invalid = new Employee();
        invalid.setSalary(-1);
        assertFalse(invalid.checkValidRecord());
    }

    @Test
    void testUnsupportedGender() {
        Employee invalid = new Employee();
        invalid.setGender("G");
        assertFalse(invalid.checkValidRecord());
    }

    @Test
    void testDigitAsFirstName() {
        Employee invalid = new Employee();
        invalid.setFirstName("1000");
        assertFalse(invalid.checkValidRecord());
    }
    @Test
    void testDigitAsLastName() {
        Employee invalid = new Employee();
        invalid.setLastName("999");
        assertFalse(invalid.checkValidRecord());
    }

    @Test
    void testZeroSalary() {
        Employee invalid = new Employee();
        invalid.setSalary(0);
        assertFalse(invalid.checkValidRecord());
    }

    @Test
    void testMiddleInitialTooLong() {
        Employee invalid = new Employee();
        invalid.setMiddleInitial("Johnson");
        assertFalse(invalid.checkValidRecord());
    }

    @Test
    void testMiddleInitialDigit() {
        Employee invalid = new Employee();
        invalid.setMiddleInitial("9");
        assertFalse(invalid.checkValidRecord());
    }

    @Test
    void DobInFuture() {
        Employee invalid = new Employee();
        Calendar cal = Calendar.getInstance(); // today
        invalid.setDateOfJoining(cal.getTime());
        cal.add(Calendar.DATE, 7); // 7 days from today
        invalid.setDateOfBirth(cal.getTime()); // in the future
        assertFalse(invalid.checkValidRecord());
    }

    @Test
    void DojInFuture() {
        Employee invalid = new Employee();
        Calendar cal = Calendar.getInstance(); // today
        invalid.setDateOfBirth(cal.getTime()); // today
        cal.add(Calendar.DATE, 7); // 7 days from today
        invalid.setDateOfJoining(cal.getTime()); // in the future
        assertFalse(invalid.checkValidRecord());
    }

    @Test
    void DojAfterDob() {
        Employee invalid = new Employee();
        Calendar cal = Calendar.getInstance(); // today
        invalid.setDateOfBirth(cal.getTime()); // today
        cal.add(Calendar.DATE, -100); // 100 days ago
        invalid.setDateOfJoining(cal.getTime()); // prior to date of birth
        assertFalse(invalid.checkValidRecord());
    }


}