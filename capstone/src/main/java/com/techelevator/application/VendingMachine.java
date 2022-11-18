package com.techelevator.application;

import com.techelevator.models.Inventory;
import com.techelevator.models.Transaction;
import com.techelevator.models.file_io.ProductLoader;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.techelevator.models.file_io.ProductLoader.LoadProductList;

public class VendingMachine
{


    public void run()
    {
        // run main menu
        MainMenu();

        // run purchase menu


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
                // display products & purchase
                Inventory.displayInventory();
                System.out.println();
                // purchase product
                UserOutput.displayPurchaseMenu(transaction);
                purchaseMenu(transaction);

            }
            else if (mainMenuSelection.equals("2"))
            {
                // exit program
                System.exit(0);
            }
            else if (mainMenuSelection.equals("3"))
            {
                // Secret bonus sales report easter egg
            }
            else
            {
                System.out.println("Not a valid menu option.");
            }
        }
    }

    public void purchaseMenu(Transaction transaction)
    {
        while (true)
        {
            // get user selection
            String purchaseMenuSelection = UserInput.getPurchaseMenuSelection();
            if (purchaseMenuSelection.equals("1"))
            {
                // Display add money menu

                UserOutput.displayFeedMoney();
                BigDecimal userFunds = UserInput.getMoney();
                transaction.addMoney(userFunds.setScale(2, RoundingMode.UNNECESSARY));
            }

            else if (purchaseMenuSelection.equals("2"))
            {
                // Display select product menu
                UserOutput.displayEnterProduct();
                String productSelection = UserInput.getPurchaseItemSelection();
                transaction.purchaseItem(productSelection);

            }

            else if (purchaseMenuSelection.equals("3"))
            {
                transaction.finishTransaction();
                transaction = new Transaction();
            }

            else
            {
                System.out.println("Not a valid menu option.");
            }

            UserOutput.displayPurchaseMenu(transaction);
        }
    }
}





