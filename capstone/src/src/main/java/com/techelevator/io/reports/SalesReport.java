package com.techelevator.io.reports;

import com.techelevator.io.ProductLoader;
import com.techelevator.models.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReport {

    /*
    Stackers|1
    Grain Waves|0
    etc...

    **TOTAL SALES** $11.05
     */


    private static Map<String, Integer> totalProductSales = new HashMap<>();

    private static BigDecimal totalSales;

    public static Map<String, Integer> getTotalProductSales()
    {
        return totalProductSales;
    }

    public static BigDecimal getTotalSales()
    {
        return totalSales;
    }

    public static void setProductSalesMapToZero()
    {
        totalSales = new BigDecimal("0.00");
        List<Product> productList = ProductLoader.LoadProductList();
        for (Product product: productList)
        {
            totalProductSales.put(product.getName(), 0);
        }
    }

    public static void updateMapFromFile(String name, int quantity)
    {
        totalProductSales.put(name, quantity);
    }

    public static void updateTotalSalesFromFile(BigDecimal sales)
    {
        totalSales = totalSales.add(sales);
    }

    public static void update(Product product)
    {
        int totalSold = totalProductSales.get(product.getName());
        totalSold++;
        totalProductSales.put(product.getName(), totalSold);

        BigDecimal price = product.getPrice();
        totalSales = totalSales.add(price);
    }

    public static void replaceSalesReport()
    {
        SalesReportWriter.updateSalesReportFile(totalProductSales, totalSales);
    }





}
