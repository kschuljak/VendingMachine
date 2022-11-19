package com.techelevator.application;

import com.techelevator.models.Inventory;
import com.techelevator.models.Transaction;
import com.techelevator.models.file_io.SalesReport;
import com.techelevator.models.file_io.SalesReportReader;
import com.techelevator.ui.Spinner;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;
import com.techelevator.view.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachine
{
    public void run()
    {
        // run main menu
        SalesReport.setProductSalesMapToZero();
        SalesReportReader.readSalesReport();
        mainMenu();
    }

    public void mainMenu()
    {
        Transaction transaction = new Transaction();

        while (true)
        {
            System.out.print(Console.CLEAR_SCREEN);
            // display home screen
            UserOutput.displayMainMenu();
            String mainMenuSelection = UserInput.getMainMenuSelection();

            if (mainMenuSelection.equals("1"))
            {
                // purchase product menu
                System.out.print(Console.CLEAR_SCREEN);
                UserOutput.displayPurchaseMenu(transaction);
                purchaseMenu(transaction);
            }
            // exit program
            else if (mainMenuSelection.equals("2"))
            {
                SalesReport.replaceSalesReport();
                System.exit(0);
            }
            else if (mainMenuSelection.equals("3"))
            {
                System.out.print(Console.CLEAR_SCREEN);
                SalesReport.replaceSalesReport();
                UserOutput.displaySalesReport();

            }
            else {System.out.println("Not a valid menu option.");}
        }
    }

    public void purchaseMenu(Transaction transaction)
    {
        while (true)
        {
            // get user selection
            String purchaseMenuSelection = UserInput.getPurchaseMenuSelection();
            // Display inventory
            if (purchaseMenuSelection.equals("1")) {Inventory.displayInventory();}
            // Display addMoneyMenu()
            else if (purchaseMenuSelection.equals("2")) {addMoneyMenu(transaction);}
            // Display select product menu
            else if (purchaseMenuSelection.equals("3"))
            {
                selectProductMenu(transaction);
            }
            else if (purchaseMenuSelection.equals("4"))
            {
                // Finish transaction
                transaction.finishTransaction();
                transaction = new Transaction();
                mainMenu();
            }
            else if (purchaseMenuSelection.equalsIgnoreCase("kick"))
            {
                Inventory.kickTheVendingMachine();
                Inventory.displayInventory();
                UserOutput.kickedVendingMachineMessage();
            }
            else {System.out.println("Not a valid menu option.");}
            UserOutput.displayPurchaseMenu(transaction);
        }
    }

    public void addMoneyMenu(Transaction transaction)
    {
        System.out.print(Console.CLEAR_SCREEN);
        UserOutput.displayFeedMoney();
        BigDecimal userFunds = UserInput.getMoney();
        transaction.addMoney(userFunds.setScale(2, RoundingMode.UNNECESSARY));
    }

    public void selectProductMenu(Transaction transaction)
    {
        System.out.print(Console.CLEAR_SCREEN);
        UserOutput.displayEnterProduct();
        String productSelection = UserInput.getPurchaseItemSelection();
        UserOutput.loadingBar();
        transaction.purchaseItem(productSelection);

    }

    public void spashScreen()
    {
        UserOutput.displaySplashScreen();
    }


}





