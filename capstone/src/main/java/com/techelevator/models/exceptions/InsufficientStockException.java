package com.techelevator.models.exceptions;

import java.math.BigDecimal;

public class InsufficientStockException extends Exception{

    private int itemStock;

    public InsufficientStockException (String message, int itemStock)
    {
        super(message);

        this.itemStock = itemStock;
    }

    public int getItemStock() {return this.itemStock;}

    @Override
    public String getMessage()
    {
        String message = super.getMessage();


        return message + "\n Item Stock: " + String.valueOf(itemStock);
    }

}
