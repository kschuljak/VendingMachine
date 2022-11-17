package com.techelevator.models.file_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProductLoader {

    public static void LoadProductList()
        {
            File products = new File("data/vendingmachine.csv");
            try (Scanner productloader = new Scanner(products))
            {
                while(productloader.hasNextLine())
                {
                    String line = productloader.nextLine();
                    System.out.println(line);
                }

            }
            catch (FileNotFoundException ex) {System.out.println(ex.getMessage());}
        }


}
