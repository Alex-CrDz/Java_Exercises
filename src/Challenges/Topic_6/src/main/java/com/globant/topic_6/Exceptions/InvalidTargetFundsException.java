package com.globant.topic_6.Exceptions;

public class InvalidTargetFundsException extends Exception{

    public static String MESSAGE = "The target funds exceed the triple of transfer mount";

    public InvalidTargetFundsException() {
        super(MESSAGE);
    }
}
