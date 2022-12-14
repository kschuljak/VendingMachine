package com.techelevator.io.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class SalesReportReader {

    public static void readSalesReport()
    {
        File file = new File("C:\\Users\\Student\\workspace\\module-1-week-4-pair-8\\capstone\\reports\\SALES_REPORT.txt");

        try (Scanner reader = new Scanner(file))
        {
            while(reader.hasNextLine())
            {
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
                    int quantity = Integer.parseInt(stringQuantity);
                    SalesReport.updateMapFromFile(name, quantity);
                }
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}
