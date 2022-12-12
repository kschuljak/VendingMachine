package com.techelevator.services;

import com.techelevator.exceptions.InvalidFundsException;
import com.techelevator.io.ProductLoader;
import com.techelevator.models.Product;
import com.techelevator.services.Transaction;
import com.techelevator.validators.Validator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TransactionTest {

    Transaction transaction = new Transaction();
    List<Product> productList = ProductLoader.LoadProductList();

    final BigDecimal ONE_DOLLAR = new BigDecimal("1.00");
    final BigDecimal FIVE_DOLLARS = new BigDecimal("5.00");
    final BigDecimal TEN_DOLLARS = new BigDecimal("10.00");
    final BigDecimal TWENTY_DOLLARS = new BigDecimal("20.00");

    @Before
    public void setUp() {
        transaction = new Transaction();
    }



    @Test
    public void addMoney_Should_IncreaseRemainingFunds_ByGivenAmount_GivenValidMoney() throws InvalidFundsException {

        //arrange
        // if given $1, $5, $10, or $20, should return true
        BigDecimal expectedNewTotal = new BigDecimal("36.00");

        //act
        if (Validator.isMoneyValid(ONE_DOLLAR)) transaction.addMoney("1");
        if (Validator.isMoneyValid(FIVE_DOLLARS)) transaction.addMoney("5");
        if (Validator.isMoneyValid(TEN_DOLLARS)) transaction.addMoney("10");
        if (Validator.isMoneyValid(TWENTY_DOLLARS)) transaction.addMoney("20");


        BigDecimal actualTotal = transaction.getRemainingFunds();

        //assert
        assertTrue("Because $1, $5, $10, and $20 are valid bill types", actualTotal.equals(expectedNewTotal));
    }

    @Test
    public void addMoney_Should_Not_UpdateRemainingFunds_GivenInvalidMoney() throws InvalidFundsException {

        //arrange
        transaction.setRemainingFunds(new BigDecimal("0.00"));
        BigDecimal expectedNewTotal = new BigDecimal("0.00");

        BigDecimal given = new BigDecimal("6.50");
        BigDecimal given2 = new BigDecimal("17.00");
        BigDecimal given3 = new BigDecimal("-1.00");

        String s1 = "6.50";
        String s2 = "17.00";
        String s3 = "-1.00";

        //act
        if (Validator.isMoneyValid(given)) transaction.addMoney(s1);
        if (Validator.isMoneyValid(given2)) transaction.addMoney(s2);
        if (Validator.isMoneyValid(given3)) transaction.addMoney(s3);

        BigDecimal actual = transaction.getRemainingFunds();

        //assert
        assertTrue("Because invalid amounts should not update total", expectedNewTotal.equals(actual));
    }

    @Test
    public void spendMoney_Should_SubtractFromRemainingFunds_ByAmountSpent() {

        //arrange
        transaction.setRemainingFunds(TWENTY_DOLLARS);

        BigDecimal itemCost1 = new BigDecimal("2.50");
        BigDecimal itemCost2 = new BigDecimal("1.00");
        BigDecimal itemCost3 = new BigDecimal("1.75");

        Product one = new Product("a1", "chips1", itemCost1, "chips");
        Product two = new Product("a2", "chips2", itemCost2, "chips");
        Product three = new Product("3", "chips3", itemCost3, "chips");

        BigDecimal expected = new BigDecimal("14.75");

        //act
        transaction.spendMoney(one);
        transaction.spendMoney(two);
        transaction.spendMoney(three);

        BigDecimal actual = transaction.getRemainingFunds();

        //assert
        assertEquals("Because 20 to start; (2.50 + 1.00 + 1.75) = 5.25; (20.00 - 5.25) = 14.75 remaining", expected, actual);
    }

    @Test
    public void updatePurchases_Should_UpdatePurchaseList_WithProduct_WhenGivenProduct() {

        //arrange
        Map<Product, Integer> productsPurchased = transaction.getProductsPurchased();
        Product one = new Product("a1", "chips1", new BigDecimal("1.00"), "chips");
        Product two = new Product("a2", "chips2", new BigDecimal("1.00"), "chips");
        Product three = new Product("3", "chips3", new BigDecimal("1.00"), "chips");

        //act
        transaction.updatePurchases(one);
        transaction.updatePurchases(two);
        transaction.updatePurchases(one);
        transaction.updatePurchases(three);
        transaction.updatePurchases(one);
        transaction.updatePurchases(three);

        int expected1value = 3;
        int expected2value = 1;
        int expected3value = 2;

        boolean actual1 = productsPurchased.containsKey(one);
        int actual1value = productsPurchased.get(one);

        boolean actual2 = productsPurchased.containsKey(two);
        int actual2value = productsPurchased.get(two);

        boolean actual3 = productsPurchased.containsKey(three);
        int actual3value = productsPurchased.get(three);

        //assert
        assertTrue("Because Product 'one' should be added to the map when passed in as argument", actual1);
        assertEquals("Because Product 'one' passed to updatePurchases three times", actual1value, expected1value);

        assertTrue("Because Product 'two' should be added to the map when passed in as argument", actual2);
        assertEquals("Because Product 'two' passed to updatePurchases one time", actual2value, expected2value);

        assertTrue("Because Product 'three' should be added to the map when passed in as argument", actual3);
        assertEquals("Because Product 'three' passed to updatePurchases two times", actual3value, expected3value);
    }

}
