package com.exhlt.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    //step1
    @Test
    public void shouldAddNumbersAndReturn0forEmptyInput() throws Exception {
        assertEquals(1 + 2, stringCalculator.add("1,2"));
        assertEquals(3 + 4, stringCalculator.add("3,4"));
        assertEquals(0, stringCalculator.add(""));
        assertEquals(0, stringCalculator.add("      "));
    }

    //step 2
    @Test
    public void shouldHandleUnknownAmountOfNumbers() throws Exception {
        assertEquals(1 + 5 + 2 + 6 + 3, stringCalculator.add("1,5,2,6,3"));
    }

    //step 3
    @Test
    public void shouldBeAbleToHandleNewLineBetweenNumbers() throws Exception {
        assertEquals(1 + 5, stringCalculator.add("1\n5"));
        assertEquals(1 + 5 + 8 + 9 + 3, stringCalculator.add("1\n5,8,9\n3,100"));
        assertEquals(9 + 8 + 3, stringCalculator.add("9\n8\n3"));
        assertEquals(9 + 8 + 3 + 8 + 2 + 3, stringCalculator.add("9\n8\n3,8\n2,3"));
        assertEquals(9 + 8 + 3 + 8 + 2 + 3, stringCalculator.add("9\n8\n3,8\n2,3"));
    }

    // step 4
    @Test
    public void shouldSupportDifferentDelimiters() throws Exception {
        assertEquals(5 + 6 + 8, stringCalculator.add("//;\n5;6;8"));
        assertEquals(5 + 6 + 8, stringCalculator.add("//,\n5,6,8"));
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.add("//;\n5,6*;8");
        });
    }

    // step 5
    @Test
    public void shouldThrowExceptionWhenAddingNegativeNumber() throws Exception {
        assertThrows(Exception.class, () -> {
            stringCalculator.add("//;\n5;6;-8");
        });

    }

    //step 6
    @Test
    public void testNumberBiggerThan1000ShouldBeIgnored() throws Exception {
        assertEquals(120 + 500 + 100, stringCalculator.add("1001,120,500,100,2500"));
        assertEquals(0 + 0 + 0, stringCalculator.add("1002,1025,2556"));
    }

    //step 7
    @Test
    public void testShouldDelimiterCanBeAnyLength() {


    }

    //step 8
    @Test
    public void testShouldHandleMultipleDelimeters() {


    }


}