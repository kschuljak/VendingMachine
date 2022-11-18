package com.techelevator.models;

import com.techelevator.models.file_io.ProductLoader;
import com.techelevator.models.products.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private static List<Product> productList = ProductLoader.LoadProductList();

    public static void displayInventory()
    {

        for (Product product : productList)
        {
            String id = product.getSlotID();
            String name = product.getName();
            BigDecimal price = product.getPrice();
            int quantity = product.getQuantity();
            if (quantity == 0) {System.out.println("[" + id + "] " + name + " - $" + price + " - SOLD OUT");}
            else {System.out.println("[" + id + "] " + name + " - $" + price + " - Left: " + quantity);}
        }
        System.out.println();
    }

    public static void updateInventory(Product product, int quantity)
    {
        int currentQuantity = product.getQuantity();
        product.setQuantity(currentQuantity-= quantity);
    }

    public static List<Product> getProductList() {
        return productList;
    }
}


