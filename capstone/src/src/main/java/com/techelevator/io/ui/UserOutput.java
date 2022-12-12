package com.techelevator.io.ui;

import com.techelevator.services.Transaction;
import com.techelevator.io.reports.SalesReport;
import com.techelevator.view.Colors;
import com.techelevator.view.Console;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class UserOutput {

    public void displayMainMenu()
    {
        displaySplashScreen();
        System.out.println("=========================");
        System.out.println("1) Browse Vending Machine");
        System.out.println("2) Exit");
        System.out.println("=========================");
    }

    public void displayPurchaseMenu(Transaction transaction)
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

    public void displayRemainingFunds(String funds){
        String currentFunds = ("* Current Funds: $" + funds + " *");
        int length = currentFunds.length();
        if (funds.equals("0.00"))
        {
            System.out.println();
            System.out.println(createLine(length));
            System.out.println("* Current Funds: " + Colors.RED + "$" + funds + Colors.RESET + " *");
            System.out.println(createLine(length));
            System.out.println();
        }
        else createBorder(currentFunds);
    }

    public void displayFeedMoney()
    {
        System.out.print("Enter dollar amount added (in whole dollars): ");
    }

    public void displayEnterProduct()
    {
        System.out.print("Enter product selection: ");
    }

    public void displayPurchaseSuccess(String name)
    {
        System.out.println();
        System.out.println(italicize("*Ca-THUNK* ") + name + " dispensed!");
    }

    public void displayItemTypeReturnMessage(String type) {
        switch (type.toLowerCase()) {
            case "chip":
                System.out.println(Colors.PURPLE + "Crunch Crunch, Yum!" + Colors.RESET);
                break;
            case "candy":
                System.out.println(Colors.PURPLE + "Munch Munch, Yum!" + Colors.RESET);
                break;
            case "drink":
                System.out.println(Colors.PURPLE + "Glug Glug, Yum!" + Colors.RESET);
                break;
            case "gum":
                System.out.println(Colors.PURPLE + "Chew Chew, Yum!" + Colors.RESET);
                break;
            default:
                break;
        }
    }

    public void displaySummaryIntro() {
        displaySummaryDivider();
        System.out.println();
        System.out.println("--------------------");
        System.out.println("  Items Purchased   ");
        System.out.println("--------------------");
    }

    public void displayItemSummary(String item, String amount)
    {
       System.out.println(amount + " " + item);
    }

    public void displayChange(int quarters, int dimes, int nickels)
    {
        System.out.println();
        System.out.println("--------------------");
        System.out.println("  Change Dispensed  ");
        System.out.println("--------------------");
        String quarterString = formatChangeString("Quarters: ");
        System.out.println(quarterString + quarters);
        String dimeString = formatChangeString("Dimes: ");
        System.out.println(dimeString + dimes);
        String nickleString = formatChangeString("Nickels: ");
        System.out.println(nickleString + nickels);
        System.out.println();
        displayThankYou();
    }

    public String formatChangeString(String coin)
    {
        return String.format("%13s", coin);
    }

    public void displaySalesReport(){
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
        System.out.println(italicize("Last updated: " + lastUpdatedDate + " " + lastUpdatedTime));

        // Waits for user input before returning to main menu.
        Scanner input = new Scanner(System.in);
        System.out.println("Press enter to return to main menu...");
        input.nextLine();
    }

    public void displayProductWithStock(String id, String name, BigDecimal price, int quantity)
    {
        System.out.println(Colors.PURPLE + "[" + id + "] " + Colors.RESET + name + " $" + price + " " + Colors.PURPLE + "[stock: " + quantity + "]" + Colors.RESET);
    }

    public void displayProductSoldOut(String id, String name, BigDecimal price)
    {
        System.out.println(Colors.PURPLE + "[" + id + "] " + Colors.RESET + name + " $" + price + " " + Colors.RED + "[SOLD OUT]" + Colors.RESET);
    }

    public void kickedVendingMachineMessage()
    {
        System.out.println(Colors.RED_BACKGROUND + Colors.BLACK + italicize(" An avalanche of snacks and soda spills out at your feet, emptying the vending machine! ") + Colors.RESET);
    }

    public void createBorder(String string)
    {
        int stringLength = string.length();
        String formatBorder = createLine(stringLength);

        System.out.println();
        System.out.println(formatBorder);
        System.out.println(string);
        System.out.println(formatBorder);
        System.out.println();
    }

    public String createLine(int length)
    {
        String formatLength = "%-" + length + "s";
        String line = "";
        return String.format(formatLength, line).replace(' ', '*');
    }

    public static void displayErrorMessage(String message)
    {
        System.out.println(Colors.RED + message + Colors.RESET);
        System.out.println();
    }

    public static void clearScreen() {
        System.out.print(Console.CLEAR_SCREEN);
    }


    public void displaySummaryDivider()
    {
        System.out.println(Colors.PURPLE + "*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*" + Colors.RESET);
    }


    public void displayThankYou()
    {
        System.out.println(Colors.PURPLE + "*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("                 THANK YOU FOR SHOPPING WITH US TODAY!");
        System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*" + Colors.RESET);
        System.out.println();
    }

    public void displaySplashScreen()
    {
        System.out.println(italicize(Colors.PURPLE + "\n                                                 An Umbrella Corp Product" + Colors.RESET));
        System.out.println("  _   _______  _____  ____      __  ______ _______________  ___  ___  ___ ");
        System.out.println(" | | / / __/ |/ / _ \\/ __ \\____/  |/  / _ /_  __/  _/ ___/ ( _ )/ _ \\/ _ \\");
        System.out.println(" | |/ / _//    / // / /_/ /___/ /|_/ / __ |/ / _/ // /__  / _  / // / // /");
        System.out.println(" |___/___/_/|_/____/\\____/   /_/  /_/_/ |_/_/ /___/\\___/  \\___/\\___/\\___/ ");
        System.out.println(italicize(Colors.PURPLE + "                        Your one-stop shop for snacks, sweets, and sodas!\n" + Colors.RESET));
    }

    public String italicize(String text)
    {
        return "\033[3m" + text + "\033[0m";
    }
}
