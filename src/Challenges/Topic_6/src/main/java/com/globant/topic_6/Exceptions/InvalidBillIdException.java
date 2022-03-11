package com.globant.topic_6.Exceptions;

public class InvalidBillIdException extends Exception {

    public static String MESSAGE = "The Bill identifier not match with expected format";

    public InvalidBillIdException() {
        super(MESSAGE);
    }
}
