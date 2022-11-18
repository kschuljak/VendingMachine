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
        boolean valueForOne = transaction.isMoneyValid(ONE_DOLLAR);
        boolean valueForFive = transaction.isMoneyValid(FIVE_DOLLARS);
        boolean valueForTen = transaction.isMoneyValid(TEN_DOLLARS);
        boolean valueForTwenty = transaction.isMoneyValid(TWENTY_DOLLARS);

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
        boolean value1 = transaction.isMoneyValid(num1);
        boolean value2 = transaction.isMoneyValid(num2);
        boolean value3 = transaction.isMoneyValid(num3);
        boolean value4 = transaction.isMoneyValid(num4);

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

    @Test
    public void isSelectionValid_Should_ReturnTrue_IfValidOption_AtoD_1to4(){

        //arrange
        String str1 = "A4";
        String str2 = "B3";
        String str3 = "C2";
        String str4 = "D1";

        //act
        boolean actual1 = transaction.isItemSelectionValid(str1);
        boolean actual2 = transaction.isItemSelectionValid(str1);
        boolean actual3 = transaction.isItemSelectionValid(str1);
        boolean actual4 = transaction.isItemSelectionValid(str1);

        //assert
        assertTrue("because A4 is valid", actual1);
        assertTrue("because B3 is valid", actual2);
        assertTrue("because C2 is valid", actual3);
        assertTrue("because D1 is valid", actual4);
    }


}
