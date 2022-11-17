package com.techelevator.models;

import java.math.BigDecimal;

public class Transaction {

    private static BigDecimal remainingFunds;

    public BigDecimal getRemainingFunds() {
        return remainingFunds;
    }
    public void setRemainingFunds(BigDecimal remainingFunds) {
        this.remainingFunds = remainingFunds;
    }

    public Transaction() {
        BigDecimal remainingFunds = new BigDecimal("0");
    }


    public static void addMoney(BigDecimal amount){
        remainingFunds = remainingFunds.add(amount);
    }


    public void purchaseItem(String itemID){
        

    }


}
