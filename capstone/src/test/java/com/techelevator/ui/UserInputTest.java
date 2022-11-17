package com.techelevator.ui;

import com.techelevator.models.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class UserInputTest {

    UserInput userInput = new UserInput();
    Transaction transaction = new Transaction();

    final BigDecimal ONE_DOLLAR = new BigDecimal("1.00");
    final BigDecimal FIVE_DOLLARS = new BigDecimal("5.00");
    final BigDecimal TEN_DOLLARS = new BigDecimal("10.00");
    final BigDecimal TWENTY_DOLLARS = new BigDecimal("20.00");

    @Before
    public void setUp() throws Exception {
        userInput = new UserInput();
        transaction =new Transaction();
    }

    @Test
    public void feedMoney_Should_CallTransactionAddMoney_GivenValidMoney() {

        //arrange
        // if given $1, $5, $10, or $20, should return true
        BigDecimal expectedOne = ONE_DOLLAR;
        BigDecimal expectedFive = FIVE_DOLLARS;
        BigDecimal expectedTen = TEN_DOLLARS;
        BigDecimal expectedTwenty = TWENTY_DOLLARS;

        //act



        //assert
    }

    @Test
    public void feedMoney_Should_Fail_GivenInvalidMoney() {

        //arrange

        //act

        //assert
    }

    @Test
    public void isMoneyValid_Should_ReturnTrue_GivenValidMoney() {
    }

    @Test
    public void isMoneyValid_Should_ReturnFalse_AndThrowError_GivenInvalidMoney() {
    }
}
