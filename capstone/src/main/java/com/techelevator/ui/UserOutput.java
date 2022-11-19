package com.techelevator.ui;

import com.techelevator.models.Transaction;
import com.techelevator.models.file_io.SalesReport;
import com.techelevator.models.products.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserOutput {



    public static void displayMainMenu()
    {
        System.out.println();
        System.out.println("1) Browse Vending Machine");
        System.out.println("2) Exit");
    }

    public static void displayPurchaseMenu(Transaction transaction)
    {
        if (transaction != null)
        {
            BigDecimal remainingFunds = transaction.getRemainingFunds();
            System.out.println();
            System.out.println("Current Funds: $" + remainingFunds.toString());
        }
        System.out.println();
        System.out.println("1) Display inventory");
        System.out.println("2) Add Money");
        System.out.println("3) Select Product");
        System.out.println("4) Finish Transaction");
    }

    public static void displayFeedMoney()
    {
        System.out.print("Enter dollar amount added (in whole dollars): ");
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
        System.out.println();
        System.out.println("Change Dispensed: ");
        System.out.println("-----------------");
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        System.out.println("Pennies: " + pennies);
    }

    public static void displaySalesReport(){
        Map<String, Integer> totalProductSales = SalesReport.getTotalProductSales();
        for (Map.Entry<String, Integer> product: totalProductSales.entrySet()) {
            String productName = product.getKey();
            int value = product.getValue();

            System.out.println(productName + "|" + value);
        }
        System.out.println();

        BigDecimal totalSales = SalesReport.getTotalSales();
        String sales = totalSales.toString();
        System.out.println("**TOTAL SALES** $" + sales);
    }

    public static void displayProductWithStock(String id, String name, BigDecimal price, int quantity)
    {
        System.out.println("[" + id + "] " + name + " $" + price + " - Left: " + quantity);
    }

    public static void displayProductSoldOut(String id, String name, BigDecimal price)
    {
        System.out.println("[" + id + "] " + name + " $" + price + " - SOLD OUT");
    }
}
