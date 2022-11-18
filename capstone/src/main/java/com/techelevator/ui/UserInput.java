package com.techelevator.ui;

import com.techelevator.models.Transaction;
import com.techelevator.models.exceptions.InvalidFundsException;
import com.techelevator.models.file_io.Logger;

import java.math.BigDecimal;
import java.util.Locale;
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

    public static String getPurchaseItemSelection()
    {
        String selection = userInput.nextLine().toLowerCase().strip();
        return selection;
    }

    public static BigDecimal getMoney()
    {
        String amount = userInput.nextLine().toLowerCase().strip();
        return new BigDecimal(amount);

    }



}
