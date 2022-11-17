package com.techelevator.ui;

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

}
