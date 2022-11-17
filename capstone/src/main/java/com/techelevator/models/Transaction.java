package com.techelevator.models;

import java.math.BigDecimal;

public class Transaction {

    private BigDecimal remainingFunds = new BigDecimal("0");

    public void addMoney(BigDecimal amount){
        remainingFunds.add(amount);
    }

}
