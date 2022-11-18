package com.techelevator.ui;

import com.techelevator.models.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TransactionTest {

    Transaction transaction = new Transaction();

    final BigDecimal ONE_DOLLAR = new BigDecimal("1.00");
    final BigDecimal FIVE_DOLLARS = new BigDecimal("5.00");
    final BigDecimal TEN_DOLLARS = new BigDecimal("10.00");
    final BigDecimal TWENTY_DOLLARS = new BigDecimal("20.00");

    @Before
    public void setUp() {
        transaction = new Transaction();
    }

    @Test
    public void isMoneyValid_Should_ReturnTrue_GivenValidMoney(){

        //arrange

        //act
        boolean valueForOne = Transaction.isMoneyValid(ONE_DOLLAR);
        boolean valueForFive = Transaction.isMoneyValid(FIVE_DOLLARS);
        boolean valueForTen = Transaction.isMoneyValid(TEN_DOLLARS);
        boolean valueForTwenty = Transaction.isMoneyValid(TWENTY_DOLLARS);

        //assert
        assertTrue("because $1 is valid", valueForOne);
        assertTrue("because $5 is valid", valueForFive);
        assertTrue("because $10 is valid", valueForTen);
        assertTrue("because $20 is valid", valueForTwenty);
    }

    @Test
    public void isMoneyValid_Should_ReturnFalse_GivenInvalidMoney() {

        //arrange

        //act
        boolean value1 = Transaction.isMoneyValid(ONE_DOLLAR);
        boolean valueForFive = Transaction.isMoneyValid(FIVE_DOLLARS);
        boolean valueForTen = Transaction.isMoneyValid(TEN_DOLLARS);
        boolean valueForTwenty = Transaction.isMoneyValid(TWENTY_DOLLARS);

        //assert
    }


    @Test
    public void addMoney_Should_UpdateRemainingFunds_GivenValidMoney() {

        //arrange
        // if given $1, $5, $10, or $20, should return true
        boolean expectedOne = true;
        boolean expectedFive = true;
        boolean expectedTen = true;
        boolean expectedTwenty = true;
        BigDecimal expectedNewTotal = new BigDecimal("36.00");

        //act
        if (transaction.isMoneyValid(ONE_DOLLAR))transaction.addMoney(ONE_DOLLAR);
        if (transaction.isMoneyValid(FIVE_DOLLARS))transaction.addMoney(FIVE_DOLLARS);
        if (transaction.isMoneyValid(TEN_DOLLARS))transaction.addMoney(TEN_DOLLARS);
        if (transaction.isMoneyValid(TWENTY_DOLLARS))transaction.addMoney(TWENTY_DOLLARS);

        BigDecimal actualTotal = transaction.getRemainingFunds();

        //assert
        assertTrue("Because $1,$5, $10, and $20 are valid bill types", actualTotal.equals(expectedNewTotal));
    }

    @Test
    public void addMoney_Should_Not_UpdateRemainingFunds_GivenInvalidMoney() {

        //arrange
        transaction.setRemainingFunds(new BigDecimal("0.00"));
        BigDecimal expectedNewTotal = new BigDecimal("0.00");
        BigDecimal given = new BigDecimal("6.50");
        BigDecimal given2 = new BigDecimal("17.00");
        BigDecimal given3 = new BigDecimal("-1.00");

        //act
        if (transaction.isMoneyValid(given)) transaction.addMoney(given);
        if (transaction.isMoneyValid(given)) transaction.addMoney(given2);
        if (transaction.isMoneyValid(given)) transaction.addMoney(given3);
        BigDecimal actual = transaction.getRemainingFunds();

        //assert
        assertTrue("Because invalid amounts should not update total", expectedNewTotal.equals(actual));
    }

}
