package com.exhlt.stringcalculator.exception;

public class StringCalculatorException extends Exception{

    private String exceptionMessage;

    public StringCalculatorException(String message) {
        super(message);
        this.exceptionMessage = message;
    }

    @Override
    public String toString() {
        return exceptionMessage;
    }

}
