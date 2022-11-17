package com.techelevator.ui;

import java.util.Scanner;

public class UserInput {

    Scanner userInput= new Scanner(System.in);

    String mainMenuSelection = userInput.nextLine();

    public String getMainMenuSelection() {
        return mainMenuSelection;
    }

    String purchaseMenuSelection = userInput.nextLine();

    public String getPurchaseMenuSelection() {
        return purchaseMenuSelection;
    }
}
