package com.techelevator.ui;

import com.techelevator.models.Transaction;

import java.util.Scanner;

public class UserOutput {

    public static void displayMainMenu()
    {
        System.out.println("1) Display Products");
        System.out.println("2) Purchase");
        System.out.println("3) Exit");
    }

    public static void displayPurchaseMenu()
    {
        System.out.println("Current Funds: " + Transaction.getRemainingFunds());
        System.out.println();
        System.out.println("1) Add Money");
        System.out.println("2) Select Product");
        System.out.println("3) Finish Transaction");
    }

    public static void displayFeedMoney()
    {
        System.out.println("Enter dollar amount added (in whole dollars)");
    }

    public static void displayEnterProduct()
    {
        System.out.print("Enter product selection: ");
    }

    public static void displayPurchaseSuccess()
    {
        System.out.println("Item received.");
    }

}
