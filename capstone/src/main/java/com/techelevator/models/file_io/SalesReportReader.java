package com.techelevator.models.file_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class SalesReportReader {

    public static void readSalesReport() {

        File file = new File("reports/SALES_REPORT.txt");

        try (Scanner reader = new Scanner(file)) {
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                if (line.startsWith("**"))
                {
                    String number = line.substring(17);
                    BigDecimal oldTotal = new BigDecimal(number);
                    SalesReport.updateTotalSalesFromFile(oldTotal);
                }
                else if (!line.equals(""))
                {
                    String[] product = line.split("\\|");
                    String name = product[0];
                    String stringQuantity = product[1];
                    Integer quantity = Integer.valueOf(stringQuantity);
                    SalesReport.updateMapFromFile(name, quantity);
                }
            }

        } catch (FileNotFoundException exception) {


        }
    }
}
