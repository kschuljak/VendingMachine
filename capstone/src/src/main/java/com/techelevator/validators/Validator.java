package com.techelevator.validators;

import com.techelevator.models.Product;

import java.math.BigDecimal;

public class Validator {

    public static boolean isItemSelectionValid(String itemID)
    {
        boolean isValidSelection = false;

        if (itemID != null) {
            itemID = (itemID).toLowerCase();

            if ((itemID.startsWith("a") || itemID.startsWith("b") || itemID.startsWith("c") || itemID.startsWith("d"))
                    && (itemID.endsWith("1") || itemID.endsWith("2") || itemID.endsWith("3") || itemID.endsWith("4")))
            {
                isValidSelection = true;
            }
        }

        return isValidSelection;
    }

    public static boolean isInStock(Product product) {

        boolean isInStock = false;
        int quantity = 0;

        if (product != null)
        {
            quantity = product.getQuantity();
        }

        if (quantity > 0)
        {
            isInStock = true;
        }

        return isInStock;
    }

    public static boolean isMoneyValid(BigDecimal money){
        BigDecimal zero = new BigDecimal("0.00");
        BigDecimal oneDollar = new BigDecimal("1.00");
        BigDecimal fiveDollars = new BigDecimal("5.00");
        BigDecimal tenDollars = new BigDecimal("10.00");
        BigDecimal twentyDollars = new BigDecimal("20.00");

        boolean isValid = false;
        if (money != null &&
                (money.equals(oneDollar) || money.equals(fiveDollars) || money.equals(tenDollars) || money.equals(twentyDollars)) &&
                money.compareTo(zero) > 0)
        {
            isValid = true;
        }
        return isValid;
    }

    public static boolean hasEnoughMoney(Product product, BigDecimal remainingFunds)
    {
        BigDecimal zero = new BigDecimal("0.00");
        BigDecimal itemCost = product.getPrice();
        boolean isValid = false;

        if (itemCost != null)
        {
            if (remainingFunds.compareTo(itemCost) >= 0)
            {
                isValid = true;
            }
        }
        return isValid;
    }

}
