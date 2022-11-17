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
    public void run()
    {
        // run main menu
        displayMainMenu();

            // get user selection

            String purchaseMenuSelection = UserInput.getPurchaseMenuSelection();

            if(purchaseMenuSelection.equals("1"))
            {
                // add money
                UserOutput.displayFeedMoney();
                UserInput.getMoney();

            }
            else if (purchaseMenuSelection.equals("2"))
            {
                // select product
            }
            else if (purchaseMenuSelection.equals(3))
            {
                // finish transaction
            }
            else
            {
                // invalid option exception
            }
    }

    public void displayMainMenu()
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
                    purchaseMenu(purchaseMenuSelection);
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

        }
    }
}



