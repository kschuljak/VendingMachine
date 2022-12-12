package com.techelevator.services;

import com.techelevator.models.Product;
import com.techelevator.services.Inventory;
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
    public void updateInventory_Should_UpdateProductQuantity_ByQuantityGiven()
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
    // error handling for 'out of stock' called in transaction before updateInventory

}
