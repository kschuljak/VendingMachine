package com.techelevator.models.file_io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;

public class SalesReportWriter {

    private static File newFile = new File("reports/SALES_REPORT.txt");

    public static void updateSalesReportFile(Map<String, Integer> totalProductSales, BigDecimal totalSales) {

        try (PrintWriter writer = new PrintWriter(newFile)) {
            for (Map.Entry<String, Integer> product: totalProductSales.entrySet()) {
                String productName = product.getKey();
                int value = product.getValue();

                writer.println(productName + "|" + value);
            }
            writer.println("");

            String sales = totalSales.toString();
            writer.println("**TOTAL SALES** $" + sales);
        } catch (IOException exception) {

        }

    }


}
