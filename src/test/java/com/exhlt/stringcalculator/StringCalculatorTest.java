package com.exhlt.stringcalculator;

import com.exhlt.stringcalculator.exception.StringCalculatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    //step1
    @Test
    public void shouldAddNumbersAndReturn0forEmptyInput() throws StringCalculatorException {
        assertEquals(1 + 2, stringCalculator.add("1,2"));
        assertEquals(3 + 4, stringCalculator.add("3,4"));
        assertEquals(0, stringCalculator.add(""));
        assertEquals(0, stringCalculator.add("      "));
    }

    //step 2
    @ParameterizedTest
    @ValueSource(strings = {"1,5,2,6,3,1000,800"})
    public void shouldHandleUnknownAmountOfNumbers(String numbers) throws StringCalculatorException {
        assertEquals(1 + 5 + 2 + 6 + 3 + 1000 + 800, stringCalculator.add(numbers));
    }

    //step 3
    @Test
    public void shouldBeAbleToHandleNewLineBetweenNumbers() throws StringCalculatorException {
        assertEquals(1 + 5, stringCalculator.add("1\n5"));
        assertEquals(1 + 5 + 8 + 9 + 3 + 100, stringCalculator.add("1\n5,8,9\n3,100"));
        assertEquals(9 + 8 + 3, stringCalculator.add("9\n8\n3"));
        assertEquals(9 + 8 + 3 + 8 + 2 + 3, stringCalculator.add("9\n8\n3,8\n2,3"));
        assertEquals(9 + 8 + 3 + 8 + 2 + 3, stringCalculator.add("9\n8\n3,8\n2,3"));
    }

    // step 4
    @Test
    public void shouldSupportDifferentDelimiters() throws StringCalculatorException {
        assertEquals(5 + 6 + 8, stringCalculator.add("//;\n5;6;8"));
        assertEquals(5 + 6 + 8, stringCalculator.add("//,\n5,6,8"));

        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.add("//;\n5,6*;8");
        });
    }

    // step 5
    @Test
    public void shouldThrowExceptionWhenAddingNegativeNumber() throws StringCalculatorException {
        assertThrows(StringCalculatorException.class, () -> {
            stringCalculator.add("//;\n5;6;-8");
        });

    }

    //step 6
    @Test
    public void testNumberBiggerThan1000ShouldBeIgnored() throws StringCalculatorException {
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