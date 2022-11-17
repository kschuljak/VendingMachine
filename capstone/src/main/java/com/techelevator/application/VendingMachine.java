package com.techelevator.application;

import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

public class VendingMachine
{
    public void run()
    {
        while(true)
        {
            // display home screen
            UserOutput.displayMainMenu();
            String mainMenuSelection = UserInput.getMainMenuSelection();

            if(mainMenuSelection.equals("1"))
            {
                // display products
            }
            else if (mainMenuSelection.equals("2"))
            {
                // purchase product
            }
            else if (mainMenuSelection.equals("3")) break;
            else if (mainMenuSelection.equals("4"))
            {
                // get sales report
            }
            else
            {
                // invalid option exception
            }


            // get user selection
            UserOutput.displayPurchaseMenu();
            String purchaseMenuSelection = UserInput.getPurchaseMenuSelection();

            if(purchaseMenuSelection.equals("1"))
            {
                // add money
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
    }    
}
