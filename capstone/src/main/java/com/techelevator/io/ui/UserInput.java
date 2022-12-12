package com.techelevator.io.ui;

import com.techelevator.io.logs.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class UserInput {

    private static Scanner userInput= new Scanner(System.in);

    public String getSelection(){
        String selection = userInput.nextLine().toLowerCase().strip();
        return selection;
    }

}
