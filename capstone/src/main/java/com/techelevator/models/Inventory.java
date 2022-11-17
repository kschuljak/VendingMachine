package com.techelevator.models;

import com.techelevator.models.file_io.ProductLoader;
import com.techelevator.models.products.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

private static List<Product> productList = new ArrayList<>();

public static void displayInventory()
{
    productList = ProductLoader.LoadProductList();

    for (Product product : productList)
    {
        String id = product.getSlotID();
        String name = product.getName();
        BigDecimal price = product.getPrice();
        int quantity = product.getQuantity();
        if (quantity == 0) {System.out.println("[" + id + "] " + name + " - $" + price + " - SOLD OUT");}
        else {System.out.println("[" + id + "] " + name + " - $" + price + " - Left: " + quantity);}
    }
}

public static void updateInventory(Product product, int quantity)
{
    int currentQuantity = product.getQuantity();
    if (currentQuantity >= quantity) {product.setQuantity(currentQuantity - quantity);}
}


}


