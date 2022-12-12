package com.techelevator.services;

import com.techelevator.io.ProductLoader;
import com.techelevator.io.ui.UserOutput;
import com.techelevator.models.Product;

import java.math.BigDecimal;
import java.util.List;

public class Inventory {

    static UserOutput userOutput = new UserOutput();

    private static List<Product> productList = ProductLoader.LoadProductList();

    public static List<Product> getProductList() {
        return productList;
    }

    public static void displayInventory()
    {
        for (Product product : productList)
        {
            String id = product.getSlotID();
            String name = product.getName() + "*";
            String nameNoSpaces = name.replace(' ', '*');
            String formattedName = String.format("%-20s", nameNoSpaces).replace(' ', '-').replace('*', ' ');
            BigDecimal price = product.getPrice();
            int quantity = product.getQuantity();
            if (quantity == 0) userOutput.displayProductSoldOut(id, formattedName, price);
            else userOutput.displayProductWithStock(id, formattedName, price, quantity);
        }
        System.out.println();
    }

    public static void updateInventory(Product product, int quantity)
    {
        int currentQuantity = product.getQuantity();
        product.setQuantity(currentQuantity + quantity);
    }

    public static void kickTheVendingMachine()
    {
        for (Product product : productList)
        {
            product.setQuantity(0);
        }
    }
}


