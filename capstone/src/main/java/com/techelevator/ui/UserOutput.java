package com.techelevator.ui;

public class UserOutput {

    public static void displayMainMenu()
    {
        System.out.println("1) Display Products");
        System.out.println("2) Purchase");
        System.out.println("3) Exit");
    }

    public static void displayPurchaseMenu()
    {
        System.out.println("Current Funds: ");
        System.out.println();
        System.out.println("1) Add Money");
        System.out.println("2) Select Product;");
        System.out.println("3) Finish Transaction");
    }

}
