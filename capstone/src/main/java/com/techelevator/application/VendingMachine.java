package com.techelevator.application;

import com.techelevator.models.Inventory;
import com.techelevator.models.Transaction;
import com.techelevator.models.file_io.ProductLoader;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;

import static com.techelevator.models.file_io.ProductLoader.LoadProductList;

public class VendingMachine
{
    Transaction transaction = new Transaction();

    public void run()
    {
        // run main menu
        MainMenu();

        // run purchase menu


    }

    public void MainMenu()
    {
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
                System.out.println();
                // purchase product
                UserOutput.displayPurchaseMenu();
                purchaseMenu();

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

    public void purchaseMenu()
    {


        while (true)
        {
            // get user selection
            String purchaseMenuSelection = UserInput.getPurchaseMenuSelection();
            Transaction transaction = new Transaction();
            if (purchaseMenuSelection.equals("1"))
            {
                // Display add money menu

                UserOutput.displayFeedMoney();
                BigDecimal userFunds = UserInput.getMoney();
                transaction.addMoney(userFunds.setScale(2));
            }

            else if (purchaseMenuSelection.equals("2"))
            {
                // Display select product menu
                transaction = new Transaction();
                UserOutput.displayEnterProduct();
                String productSelection = UserInput.getPurchaseItemSelection();
                transaction.purchaseItem(productSelection);
                UserOutput.displayPurchaseSuccess();
                BigDecimal remainingFunds = transaction.getRemainingFunds();

            }

            else if (purchaseMenuSelection.equals("3"))
            {
                // Finish transaction

            }

            else
            {
                System.out.println("Not a valid menu option.");
            }

            UserOutput.displayPurchaseMenu();
        }
    }
}





