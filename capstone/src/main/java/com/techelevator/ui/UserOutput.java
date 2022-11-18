package com.techelevator.ui;

import com.techelevator.models.Transaction;
import com.techelevator.models.products.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserOutput {



    public static void displayMainMenu()
    {
        System.out.println("1) Browse Vending Machine");
        System.out.println("2) Exit");
    }

    public static void displayPurchaseMenu(Transaction transaction)
    {
        if (transaction != null)
        {
            BigDecimal remainingFunds = transaction.getRemainingFunds();
            System.out.println("Current Funds: " + remainingFunds.toString());
        }
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
            case "chip":
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

    public static void displaySummaryIntro() {
        System.out.println("Thank you for shopping with us today!");
        System.out.println();
        System.out.println("Items Purchased: ");
    }

    public static void displayItemSummary(String item, String amount)
    {
       System.out.println(item + "'s Purchased: " + amount);
    }

    public static void displayChange(int quarters, int dimes, int nickels, int pennies)
    {
        System.out.println("Change Dispensed: ");
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        System.out.println("Pennies: " + pennies);
    }
}
