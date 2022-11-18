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
        while(true)
        {
            // display home screen
            UserOutput.displayMainMenu();
            String mainMenuSelection = UserInput.getMainMenuSelection();


            switch (mainMenuSelection)
            {
                case "1":
                    // display products
                    Inventory.displayInventory();
                    break;
                case "2":
                    // purchase product
                    UserOutput.displayPurchaseMenu();
                    String purchaseMenuSelection = UserInput.getPurchaseMenuSelection();
                    //purchaseMenu(purchaseMenuSelection);
                    break;
                case "3":
                    // Exit program/vending machine
                    System.exit(0);
                    break;
                case "4":
                    // get sales report
                    break;
                default:
                    // invalid option exception
                    System.out.println("Not a valid menu option. Try again...");
                    UserOutput.displayMainMenu();
            }
        }
    }

    public void purchaseMenu()
    {
        // get user selection
        String purchaseMenuSelection = UserInput.getPurchaseMenuSelection();

        while (true)
        {
            switch (purchaseMenuSelection)
            {
                case "1":
                    UserOutput.displayFeedMoney();
                    UserInput.getMoney();
                    UserOutput.displayPurchaseMenu();
                    break;
                case "2":
                    // select product
                    UserOutput.displayEnterProduct();
                    UserInput.getPurchaseItemSelection();
                    break;
                case "3":
                    // finish transaction
                    break;
                default:
                    System.out.println("Not a valid menu option. Try again...");

            }
        }





