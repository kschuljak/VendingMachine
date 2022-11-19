package com.techelevator.models;

import com.techelevator.models.products.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class InventoryTest
{
    Product product;

    @Before
    public void setup()
    {
        product = new Product("A1", "Potato Chips", BigDecimal.valueOf(3.05), "Chip" );
    }


    @Test
    public void updateInventory_Should_UpdateProductQuantity_LessQuantityGiven()
    {
        // arrange
        product.setQuantity(5);
        int expected = 4;
        // act
        Inventory.updateInventory(product, -1);
        int actual = product.getQuantity();

        // assert
        assertEquals("passing -1 into method should reduce product quantity from 5 to 4", expected, actual);
    }

    @Test
    public void updateInventory_Should_OnlyUpdateQuantity_IfStockLeft()
    {
        // arrange
        int expected = 0;

        // act
        product.setQuantity(0);
        Inventory.updateInventory(product, -1);
        int actual = product.getQuantity();

        // assert
        assertEquals("If there is 0 stock left, amount shouldn't be updated as there is nothing to buy.", expected, actual);

    }
}
