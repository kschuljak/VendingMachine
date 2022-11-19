package com.techelevator.models.file_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class SalesReportReader {

    public void readSalesReport() {

        File file = new File("reports/SALES_REPORT.txt");

        try (Scanner reader = new Scanner(file)) {
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                if (line.startsWith("**"))
                {
                    String number = line.substring(18);
                    BigDecimal oldTotal = new BigDecimal(number);
                    SalesReport.updateTotalSalesFromFile(oldTotal);
                }
                else if (!line.equals("\n"))
                {
                    String[] product = line.split("//|");
                    String name = product[0];
                    int quantity = Integer.getInteger(product[1]);
                    SalesReport.updateMapFromFile(name, quantity);
                }
            }

        } catch (FileNotFoundException exception) {


        }
    }
}
