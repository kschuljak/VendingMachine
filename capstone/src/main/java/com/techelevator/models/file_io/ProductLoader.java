package com.techelevator.models.file_io;

import com.techelevator.models.products.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class ProductLoader{

//    @Override
//    public int compare(Product productA, Product productB)
//    {
//        return productA.getSlotID().compareTo(productB.getSlotID());
//    }

    public static List<Product> LoadProductList()
        {
            List<Product> productList = new ArrayList<>();
            File productFile = new File("data/vendingmachine.csv");
            try (Scanner productLoader = new Scanner(productFile))
            {
                while(productLoader.hasNextLine())
                {
                    String line = productLoader.nextLine();
                    String[]splitLine = line.split("\\|");
                    String productID = splitLine[0];
                    String productName = splitLine[1];
                    BigDecimal productPrice = new BigDecimal(splitLine[2]);
                    productPrice = productPrice.setScale(2);
                    String productType = splitLine[3];

                    Product product = new Product(productID, productName, productPrice, productType);

                    productList.add(product);
                }

            }
            catch (FileNotFoundException ex) {System.out.println(ex.getMessage());}

            return productList;
        }



}
