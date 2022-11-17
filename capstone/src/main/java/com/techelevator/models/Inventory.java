package com.techelevator.models;

import com.techelevator.models.file_io.ProductLoader;
import com.techelevator.models.products.Product;

import java.math.BigDecimal;
import java.util.Map;

public class Inventory {

public static void displayInventory()
{
    ProductLoader.LoadProductList();

    Map<Product, Integer> productList = ProductLoader.LoadProductList();

    for (Map.Entry<Product, Integer> product : productList.entrySet())
    {
        String id = product.getKey().getSlotID();
        String name = product.getKey().getName();
        BigDecimal price = product.getKey().getPrice();
        int quantity = product.getValue();
        System.out.println(id + " " + name + " - " + price + " - Left: " + quantity);
    }

}


}


