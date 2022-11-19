package com.techelevator.models;

import com.techelevator.models.file_io.ProductLoader;
import com.techelevator.models.products.Product;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

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
            if (quantity == 0) UserOutput.displayProductSoldOut(id, formattedName, price);
            else UserOutput.displayProductWithStock(id, formattedName, price, quantity);
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


