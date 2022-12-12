package com.techelevator.application;

import com.techelevator.services.Inventory;
import com.techelevator.services.Transaction;
import com.techelevator.exceptions.InsufficientFundsException;
import com.techelevator.exceptions.InsufficientStockException;
import com.techelevator.exceptions.InvalidFundsException;
import com.techelevator.exceptions.InvalidOptionException;
import com.techelevator.io.logs.Logger;
import com.techelevator.io.reports.SalesReport;
import com.techelevator.io.reports.SalesReportReader;
import com.techelevator.io.ui.UserInput;
import com.techelevator.io.ui.UserOutput;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachine
{
    UserOutput userOutput = new UserOutput();
    UserInput userInput = new UserInput();

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
            UserOutput.clearScreen();

            // display home screen
            userOutput.displayMainMenu();
            String mainMenuSelection = userInput.getSelection();

            if (mainMenuSelection.equals("1"))
            {
                // purchase product menu
                UserOutput.clearScreen();
                userOutput.displayPurchaseMenu(transaction);
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
                UserOutput.clearScreen();
                SalesReport.replaceSalesReport();
                userOutput.displaySalesReport();
            }
            else
            {
                UserOutput.displayErrorMessage("Not a valid menu option.");
            }
        }
    }

    public void purchaseMenu(Transaction transaction)
    {
        while (true)
        {
            // get user selection
            String purchaseMenuSelection = userInput.getSelection();
            // Display inventory
            if (purchaseMenuSelection.equals("1")) {Inventory.displayInventory();}
            // Display addMoneyMenu()
            else if (purchaseMenuSelection.equals("2")) {addMoneyMenu(transaction);}
            // Display select product menu
            else if (purchaseMenuSelection.equals("3"))
            {
                selectProductMenuItem(transaction);
            }
            else if (purchaseMenuSelection.equals("4"))
            {
                // Finish transaction
                UserOutput.clearScreen();
                transaction.finishTransaction();
                transaction = new Transaction();
                mainMenu();
            }
            else if (purchaseMenuSelection.equalsIgnoreCase("kick"))
            {
                Inventory.kickTheVendingMachine();
                Inventory.displayInventory();
                userOutput.kickedVendingMachineMessage();
            }
            else {
                UserOutput.displayErrorMessage("Not a valid menu option.");
            }
            userOutput.displayPurchaseMenu(transaction);
        }
    }

    public void addMoneyMenu(Transaction transaction)
    {
        UserOutput.clearScreen();
        userOutput.displayFeedMoney();
        String userFunds = userInput.getSelection();

        // if user enters $1, $5, $10, or $20 add money, otherwise throw Invalid Funds Exception
        try {
            transaction.addMoney(userFunds);
        }
        catch (InvalidFundsException ex) {
            UserOutput.displayErrorMessage("That is not a valid bill type! \nThis machine accepts $1, $5, $10, & $20");
            Logger.createLogEntry(ex.getMessage());
        }
    }

    public void selectProductMenuItem(Transaction transaction)
    {
        UserOutput.clearScreen();

        try {
            userOutput.displayEnterProduct();
            String productSelection = userInput.getSelection();
            transaction.purchaseItem(productSelection);
        }
        catch (InvalidOptionException ex)
        {
            UserOutput.displayErrorMessage("That is not a valid selection! \nPlease select a different item.");
            Logger.createLogEntry(ex.getMessage());
        }
        catch (InsufficientStockException ex)
        {
            UserOutput.displayErrorMessage("This item is out of stock! \nPlease select a different item.");
            Logger.createLogEntry(ex.getMessage());
        }
        catch (InsufficientFundsException ex)
        {
            UserOutput.displayErrorMessage("Insufficient funds! \nPlease add money to purchase item.");
            Logger.createLogEntry(ex.getMessage());
        }
        catch (Exception ex)
        {
            Logger.createLogEntry(ex.getMessage());
        }
    }
}





