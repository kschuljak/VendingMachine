package com.techelevator.ui;

import com.techelevator.models.Transaction;
import com.techelevator.models.exceptions.InvalidFundsException;
import com.techelevator.models.file_io.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserInput {

    private static Scanner userInput= new Scanner(System.in);

    public static String getMainMenuSelection()
    {
        String selection = userInput.nextLine().toLowerCase().strip();
        return selection;
    }

    public static String getPurchaseMenuSelection()
    {
        String selection = userInput.nextLine().toLowerCase().strip();
        return selection;
    }

    public void feedMoney()
    {
        String amount = userInput.nextLine().toLowerCase().strip();
        BigDecimal dollarsAdded = new BigDecimal(amount);
        if (!isMoneyValid(dollarsAdded)) return;
        Transaction.addMoney(dollarsAdded);
    }

    public boolean isMoneyValid(BigDecimal money){
        BigDecimal oneDollar = new BigDecimal("1.00");
        BigDecimal fiveDollars = new BigDecimal("5.00");
        BigDecimal tenDollars = new BigDecimal("10.00");
        BigDecimal twentyDollars = new BigDecimal("20.00");

        try
        {
            if (!money.equals(oneDollar) || !money.equals(fiveDollars) ||
                !money.equals(tenDollars) || !money.equals(twentyDollars))
            {
                throw new InvalidFundsException("Invalid bill type", money);
            }
        } catch (InvalidFundsException exception){

                Logger.createLogEntry(exception.getMessage());
                return false;
        }
        return true;
    }

}
