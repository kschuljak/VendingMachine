package com.techelevator.ui;

import com.techelevator.models.Transaction;

import java.util.Scanner;

public class UserOutput {



    public static void displayMainMenu()
    {
        System.out.println("1) Browse Vending Machine");
        System.out.println("2) Exit");
    }

    public static void displayPurchaseMenu()
    {
        Transaction transaction = new Transaction();
        System.out.println("Current Funds: " + transaction.getRemainingFunds());
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

    public static void displayItemTypeReturnMessage(String type) {
        switch (type.toLowerCase()) {
            case "chips":
                System.out.println("Crunch Crunch, Yum!");
                break;
            case "candy":
                System.out.println("Munch Munch, Yum!");
                break;
            case "drink":
                System.out.println("Glug Glug, Yum!");
                break;
            case "gum":
                System.out.println("Chew Chew, Yum!");
                break;
            default:
                break;
        }
    }
}
