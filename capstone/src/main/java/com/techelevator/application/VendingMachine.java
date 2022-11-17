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
            

            // get user selection
            UserOutput.displayPurchaseMenu();
        }
    }    
}
