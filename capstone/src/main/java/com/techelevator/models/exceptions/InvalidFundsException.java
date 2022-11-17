package com.techelevator.models.exceptions;

import java.math.BigDecimal;

public class InvalidFundsException extends Exception{

    // if a user enters money not in acceptable values ($1.00, $5.00, $10.00, $20.00)

    /*
        BigDecimal oneDollar = new BigDecimal("1.00");
        BigDecimal fiveDollars = new BigDecimal("5.00");
        BigDecimal tenDollars = new BigDecimal("10.00");
        BigDecimal twentyDollars = new BigDecimal("20.00");

        if (
                !dollarsAdded.equals(oneDollar) ||
                !dollarsAdded.equals(fiveDollars) ||
                !dollarsAdded.equals(tenDollars) ||
                !dollarsAdded.equals(twentyDollars)
        ){
            // invalid funds exception
        }
     */

    private BigDecimal dollarsAdded;

    public InvalidFundsException(String message, BigDecimal dollarsAdded){
        super(message);

        this.dollarsAdded = dollarsAdded;
    }

    @Override
    public String getMessage(){
        String message = super.getMessage();

        return message
                + "\nAmount Given: $" + dollarsAdded.toString()
                + "\nAccepted Bills: $1, $5, $10, & $20";
    }

}
