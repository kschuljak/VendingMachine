package com.techelevator.models.exceptions;

public class InterruptedException extends Exception{

    public InterruptedException(String message) {
        super(message);
    }

    @Override
    public String getMessage()
    {
        String message = super.getMessage();

        return message + "\n Spinner - Interrupted Exception";
    }

}
