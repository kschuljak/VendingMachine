package com.techelevator.models;

import com.techelevator.models.file_io.ProductLoader;
import com.techelevator.models.products.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Inventory {


public static void displayInventory()
{
    ProductLoader.LoadProductList();

    List<Product> productList = ProductLoader.LoadProductList();

    for (Product product : productList)
    {
        String id = product.getSlotID();
        String name = product.getName();
        BigDecimal price = product.getPrice();
        int quantity = product.getQuantity();
        System.out.println("[" + id + "] " + name + " - $" + price + " - Left: " + quantity);
    }
}

public static void updateInventory(Product product, int quantity)
{
    int currentQuantity = product.getQuantity();
    if (currentQuantity >= quantity) {product.setQuantity(currentQuantity - quantity);}
}


}


