package com.techelevator.models.file_io;

import com.techelevator.models.products.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductLoader {

    public static void LoadProductList()
        {
            Map<Product,Integer> productList = new HashMap<>();
            File productFile = new File("data/vendingmachine.csv");
            try (Scanner productloader = new Scanner(productFile))
            {
                while(productloader.hasNextLine())
                {
                    String line = productloader.nextLine();
                    String[]splitLine = line.split("\\|");
                    String productID = splitLine[0];
                    String productName = splitLine[1];
                    BigDecimal productPrice = new BigDecimal(splitLine[2]);
                    String productType = splitLine[3];

                    Product product = new Product(productID, productName, productPrice, productType);

                    productList.put(product, 5);
                }

            }
            catch (FileNotFoundException ex) {System.out.println(ex.getMessage());}
        }


}
