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
        while(true)
        {
            // display home screen
            UserOutput.displayMainMenu();
            String mainMenuSelection = UserInput.getMainMenuSelection();

//            if(mainMenuSelection.equals("1"))
//            {
//                // display products
//                Inventory.displayInventory();
//
//            }
//            else if (mainMenuSelection.equals("2"))
//            {
//                // purchase product
//            }
//            else if (mainMenuSelection.equals("3")) break;
//            else if (mainMenuSelection.equals("4"))
//            {
//                // get sales report
//            }
//            else
//            {
//                // invalid option exception
//            }

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

    public void purchaseMenu(String purchaseMenuSelection){
        if(purchaseMenuSelection.equals("1"))
        {
            // add money
            UserOutput.displayFeedMoney();
            BigDecimal addedFunds = UserInput.getMoney();
            Transaction.addMoney(addedFunds);

        }
        else if (purchaseMenuSelection.equals("2"))
        {
            // select product
            UserOutput.displayEnterProduct();
            String item = UserInput.getPurchaseItemSelection();
            Transaction.purchaseItem(item);
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

}
