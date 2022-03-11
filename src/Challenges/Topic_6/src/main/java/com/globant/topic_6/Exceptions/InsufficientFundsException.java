package com.globant.topic_6.Exceptions;

public class InsufficientFundsException extends Exception {

    public static String MESSAGE_DEFAULT = "Insufficient funds in origin account";
    public static String MESSAGE_BILL = "Insufficient funds: the account must have al least a 20% more than amount to be paid";

    public InsufficientFundsException() {
        super(MESSAGE_DEFAULT);
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
