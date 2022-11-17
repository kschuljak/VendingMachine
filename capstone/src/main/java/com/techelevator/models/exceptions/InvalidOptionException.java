package com.techelevator.models.exceptions;

public class InvalidOptionException extends Exception{

    private String itemID;

    public InvalidOptionException(String message, String itemID)
    {
        super(message);
        this.itemID = itemID;
    }

    @Override
    public String getMessage()
    {
        String message = super.getMessage();
        return message + "User input: " + itemID;

    }
}
