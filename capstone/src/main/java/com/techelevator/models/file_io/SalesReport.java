package com.techelevator.models.file_io;

import com.techelevator.models.products.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class SalesReport {

    /*
    Stackers|1
    Grain Waves|0
    Cloud Popcorn|0
    Moonpie|3
    Cowtales|0
    Wonka Bar|0
    Crunchie|0
    Cola|2
    Dr. Salt|0
    Mountain Melter|0
    Heavy|0
    U-Chews|0
    Little League Chew|1
    Chiclets|1
    Triplemint|0

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
