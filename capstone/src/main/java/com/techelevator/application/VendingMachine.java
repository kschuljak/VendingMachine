package com.techelevator.application;

import com.techelevator.models.Inventory;
import com.techelevator.models.Transaction;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachine
{
    public void run()
    {
        // run main menu
        MainMenu();
    }

    public void MainMenu()
    {
        Transaction transaction = new Transaction();

        while (true)
        {
            // display home screen
            UserOutput.displayMainMenu();
            String mainMenuSelection = UserInput.getMainMenuSelection();

            if (mainMenuSelection.equals("1"))
            {
                // purchase product menu
                UserOutput.displayPurchaseMenu(transaction);
                purchaseMenu(transaction);
            }
            // exit program
            else if (mainMenuSelection.equals("2")) {System.exit(0);}
            else if (mainMenuSelection.equals("3"))
            {
                // Secret bonus sales report easter egg
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
                MainMenu();
            }
            else {System.out.println("Not a valid menu option.");}
            UserOutput.displayPurchaseMenu(transaction);
        }
    }

    public void addMoneyMenu(Transaction transaction)
    {
        UserOutput.displayFeedMoney();
        BigDecimal userFunds = UserInput.getMoney();
        transaction.addMoney(userFunds.setScale(2, RoundingMode.UNNECESSARY));
    }

    public void selectProductMenu(Transaction transaction)
    {
        UserOutput.displayEnterProduct();
        String productSelection = UserInput.getPurchaseItemSelection();
        transaction.purchaseItem(productSelection);

    }


}





