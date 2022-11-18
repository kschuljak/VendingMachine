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
                // purchase product
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
            // Display addMoneyMenu()
            if (purchaseMenuSelection.equals("1")) {addMoneyMenu(transaction);}
            // Display select product menu
            else if (purchaseMenuSelection.equals("2")) { selectProductMenu(transaction);}
            else if (purchaseMenuSelection.equals("3"))
            {
                // Finish transaction
//                transaction.finishTransaction();
                MainMenu();
                transaction.finishTransaction();
                transaction = new Transaction();
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





