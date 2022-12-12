package com.techelevator.exceptions;

import java.math.BigDecimal;

public class InsufficientFundsException extends Exception{

    private BigDecimal totalFunds;
    private BigDecimal itemCost;

    public InsufficientFundsException (String message, BigDecimal totalFunds, BigDecimal itemCost)
    {
        super(message);

        this.totalFunds = totalFunds;
        this.itemCost = itemCost;
    }

    public BigDecimal getTotalFunds() {return this.totalFunds;}
    public BigDecimal getItemCost() {return this.itemCost;}

    @Override
    public String getMessage()
    {
        String message = super.getMessage();

        return message + "\n Item Cost: $" + itemCost.toString()
                + "\n Your funds remaining: $" + totalFunds.toString();
    }
}
