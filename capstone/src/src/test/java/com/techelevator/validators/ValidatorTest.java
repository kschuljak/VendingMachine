package com.techelevator.validators;

import com.techelevator.services.Transaction;
import com.techelevator.io.ProductLoader;
import com.techelevator.models.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ValidatorTest {

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
    public void isItemSelectionValid_Should_ReturnTrue_IfValidOption_AtoD_1to4(){

        //arrange
        String str1 = "a4";
        String str2 = "B3";
        String str3 = "c2";
        String str4 = "D1";

        //act
        boolean actual1 = Validator.isItemSelectionValid(str1);
        boolean actual2 = Validator.isItemSelectionValid(str2);
        boolean actual3 = Validator.isItemSelectionValid(str3);
        boolean actual4 = Validator.isItemSelectionValid(str4);

        //assert
        assertTrue("because a4 is valid (A-D (case insensitive), 1-4)", actual1);
        assertTrue("because B3 is valid (A-D (case insensitive), 1-4)", actual2);
        assertTrue("because c2 is valid (A-D (case insensitive), 1-4)", actual3);
        assertTrue("because D1 is valid (A-D (case insensitive), 1-4)", actual4);
    }

    @Test
    public void isItemSelectionValid_Should_ReturnFalse_IfInvalidOption_NotAtoD_Not1to4(){

        //arrange
        String str1 = "a5";
        String str2 = "f3";
        String str3 = "1";
        String str4 = "z9";

        //act
        boolean actual1 = Validator.isItemSelectionValid(str1);
        boolean actual2 = Validator.isItemSelectionValid(str2);
        boolean actual3 = Validator.isItemSelectionValid(str3);
        boolean actual4 = Validator.isItemSelectionValid(str4);

        //assert
        assertFalse("because a5 is NOT valid (A-D (case insensitive), 1-4)", actual1);
        assertFalse("because f3 is NOT valid (A-D (case insensitive), 1-4)", actual2);
        assertFalse("because 1 is NOT valid (A-D (case insensitive), 1-4)", actual3);
        assertFalse("because z9 is NOT valid (A-D (case insensitive), 1-4)", actual4);
    }

    @Test
    public void isInStock_Should_ReturnTrue_GivenItemInStock() {

        //arrange
        Product product1 = new Product("A1", "Chips1", new BigDecimal("1.00"), "chips");
        Product product2 = new Product("A2", "Chips2", new BigDecimal("1.00"), "chips");
        Product product3 = new Product("A3", "Chips3", new BigDecimal("1.00"), "chips");
        product1.setQuantity(5);
        product2.setQuantity(1);
        product3.setQuantity(3);

        //act
        boolean actual1 = Validator.isInStock(product1);
        boolean actual2 = Validator.isInStock(product2);
        boolean actual3 = Validator.isInStock(product3);

        //assert
        assertTrue("Because quantity = 5, item is in stock", actual1);
        assertTrue("Because quantity = 1, item is in stock", actual2);
        assertTrue("Because quantity = 3, item is in stock", actual3);
    }

    @Test
    public void isInStock_Should_ReturnFalse_GivenItemNotInStock() {

        //arrange
        Product product1 = new Product("A1", "Chips1", new BigDecimal("1.00"), "chips");
        Product product2 = new Product("A2", "Chips2", new BigDecimal("1.00"), "chips");
        product1.setQuantity(0);
        product2.setQuantity(-1);

        //act
        boolean actual1 = Validator.isInStock(product1);
        boolean actual2 = Validator.isInStock(product2);

        //assert
        assertFalse("Because quantity = 0, item NOT in stock", actual1);
        assertFalse("Because quantity = -1, item NOT in stock", actual2);
    }

    @Test
    public void isMoneyValid_Should_ReturnTrue_GivenValidMoney(){

        //arrange

        //act
        boolean valueForOne = Validator.isMoneyValid(ONE_DOLLAR);
        boolean valueForFive = Validator.isMoneyValid(FIVE_DOLLARS);
        boolean valueForTen = Validator.isMoneyValid(TEN_DOLLARS);
        boolean valueForTwenty = Validator.isMoneyValid(TWENTY_DOLLARS);


        //assert
        assertTrue("because $1.00 is valid", valueForOne);
        assertTrue("because $5.00 is valid", valueForFive);
        assertTrue("because $10.00 is valid", valueForTen);
        assertTrue("because $20.00 is valid", valueForTwenty);
    }

    @Test
    public void isMoneyValid_Should_ReturnFalse_GivenInvalidMoney() {

        //arrange
        BigDecimal num1 = new BigDecimal("14.00");
        BigDecimal num2 = new BigDecimal("0.55");
        BigDecimal num3 = new BigDecimal("-10");
        BigDecimal num4 = new BigDecimal("40.00");

        //act
        boolean value1 = Validator.isMoneyValid(num1);
        boolean value2 = Validator.isMoneyValid(num2);
        boolean value3 = Validator.isMoneyValid(num3);
        boolean value4 = Validator.isMoneyValid(num4);

        //assert
        assertFalse("because $14 is not valid", value1);
        assertFalse("because $0.55 is not valid", value2);
        assertFalse("because -$10 is not valid", value3);
        assertFalse("because $40 is not valid", value4);
    }

    @Test
    public void hasEnoughMoney_Should_ReturnTrue_GivenCurrentFunds_GreaterThan_OrEqualTo_ItemCost() {

        //arrange
        transaction.setRemainingFunds(new BigDecimal("5.00"));
        Product product1 = new Product("A1", "Chips1", new BigDecimal("2.20"), "chips");
        Product product2 = new Product("A2", "Chips2", new BigDecimal("5.00"), "chips");

        //act
        boolean actual1 = Validator.hasEnoughMoney(product1, transaction.getRemainingFunds());
        boolean actual2 = Validator.hasEnoughMoney(product2, transaction.getRemainingFunds());

        //assert
        assertTrue("Because current funds ($5.00) > item cost ($2.20)", actual1);
        assertTrue("Because current funds ($5.00) = item cost ($5.00)", actual2);
    }

    @Test
    public void hasEnoughMoney_Should_ReturnFalse_GivenCurrentFunds_LessThan_ItemCost() {

        //arrange
        transaction.setRemainingFunds(new BigDecimal("1.00"));
        Product product1 = new Product("A1", "Chips1", new BigDecimal("2.20"), "chips");
        Product product2 = new Product("A2", "Chips2", new BigDecimal("5.00"), "chips");

        //act
        boolean actual1 = Validator.hasEnoughMoney(product1, transaction.getRemainingFunds());
        boolean actual2 = Validator.hasEnoughMoney(product2, transaction.getRemainingFunds());

        //assert
        assertFalse("Because current funds ($1.00) < item cost ($2.20)", actual1);
        assertFalse("Because current funds ($1.00) < item cost ($5.00)", actual2);
    }

}

