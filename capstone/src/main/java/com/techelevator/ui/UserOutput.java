package com.techelevator.ui;

import com.techelevator.models.Transaction;
import com.techelevator.models.file_io.SalesReport;
import com.techelevator.models.products.Product;
import com.techelevator.view.Colors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
            String funds = remainingFunds.toString();
            displayRemainingFunds(funds);
        }
        System.out.println("1) Display Inventory");
        System.out.println("2) Add Money");
        System.out.println("3) Select Product");
        System.out.println("4) Finish Transaction");
    }

    public static void displayRemainingFunds(String funds){

        String currentFunds = ("* Current Funds: $" + funds + " *");
        createBorder(currentFunds);
    }

    public static void displayFeedMoney()
    {
        System.out.print("Enter dollar amount added (in whole dollars): ");
    }

    public static void displayEnterProduct()
    {
        System.out.print("Enter product selection: ");
    }

    public static void displayPurchaseSuccess(String name) {System.out.println("\033[3m*Ca-THUNK*\033[0m\n" + name + " dispensed!");}

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

    public static void displayChange(int quarters, int dimes, int nickels)
    {
        System.out.println();
        System.out.println("Change Dispensed: ");
        System.out.println("-----------------");
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
    }

    public static void displaySalesReport(){
        Map<String, Integer> totalProductSales = SalesReport.getTotalProductSales();

        // Sales report header
        String header = "SALES REPORT";
        createBorder(header);

        // Populates sales report data
        for (Map.Entry<String, Integer> product: totalProductSales.entrySet()) {
            String productName = product.getKey();
            int value = product.getValue();

            System.out.println(productName + "|" + value);
        }
        System.out.println();

        BigDecimal totalSales = SalesReport.getTotalSales();
        String sales = totalSales.toString();
        System.out.println("**TOTAL SALES** $" + sales );

        // Last Updated Date + Time
        String lastUpdatedDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String timePattern = "hh:mm:ss a";
        String lastUpdatedTime = LocalTime.now().format(DateTimeFormatter.ofPattern(timePattern));
        System.out.println("\033[3m\nLast updated: " + lastUpdatedDate + " " + lastUpdatedTime + "\033[0m\n");

        // Waits for user input before returning to main menu.
        Scanner input = new Scanner(System.in);
        System.out.println("Press enter to return to main menu...");
        input.nextLine();
    }

    public static void displayProductWithStock(String id, String name, BigDecimal price, int quantity)
    {
        System.out.println("[" + id + "] " + name + " $" + price + " - Left: " + quantity);
    }

    public static void displayProductSoldOut(String id, String name, BigDecimal price)
    {
        System.out.println("[" + id + "] " + name + " $" + price + " - " + Colors.RED + "SOLD OUT" + Colors.RESET);
    }

    public static void createBorder(String string)
    {
        int stringLength = string.length();
        String formatLength = "%-" + stringLength + "s";
        String border = "";
        String formatBorder = String.format(formatLength, border).replace(' ', '*');

        System.out.println();
        System.out.println(formatBorder);
        System.out.println(string);
        System.out.println(formatBorder);
        System.out.println();
    }
}
