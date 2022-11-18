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
        BigDecimal num1 = new BigDecimal("14.00");
        BigDecimal num2 = new BigDecimal("0.55");
        BigDecimal num3 = new BigDecimal("-10");
        BigDecimal num4 = new BigDecimal("40.00");

        //act
        boolean value1 = Transaction.isMoneyValid(num1);
        boolean value2 = Transaction.isMoneyValid(num2);
        boolean value3 = Transaction.isMoneyValid(num3);
        boolean value4 = Transaction.isMoneyValid(num4);

        //assert
        assertFalse("because $14 is not valid", value1);
        assertFalse("because $0.55 is not valid", value2);
        assertFalse("because -$10 is not valid", value3);
        assertFalse("because $40 is not valid", value4);
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
        if (Transaction.isMoneyValid(ONE_DOLLAR))Transaction.addMoney(ONE_DOLLAR);
        if (Transaction.isMoneyValid(FIVE_DOLLARS))Transaction.addMoney(FIVE_DOLLARS);
        if (Transaction.isMoneyValid(TEN_DOLLARS))Transaction.addMoney(TEN_DOLLARS);
        if (Transaction.isMoneyValid(TWENTY_DOLLARS))Transaction.addMoney(TWENTY_DOLLARS);

        BigDecimal actualTotal = Transaction.getRemainingFunds();

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
        if (Transaction.isMoneyValid(given)) Transaction.addMoney(given);
        if (Transaction.isMoneyValid(given)) Transaction.addMoney(given2);
        if (Transaction.isMoneyValid(given)) Transaction.addMoney(given3);
        BigDecimal actual = Transaction.getRemainingFunds();

        //assert
        assertTrue("Because invalid amounts should not update total", expectedNewTotal.equals(actual));
    }

    @Test
    public void isSelectionValid_Should_ReturnTrue_IfValidOption_AtoD_1to4(){

        //arrange
        String str1 = "A4";
        String str2 = "B3";
        String str3 = "C2";
        String str4 = "D1";

        //act
        Transaction.isItemSelectionValid(str1);
    }

}
