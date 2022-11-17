package com.techelevator.models;

<<<<<<< HEAD
import com.techelevator.models.exceptions.InsufficientFundsException;
=======
import com.techelevator.models.exceptions.InvalidFundsException;
import com.techelevator.models.file_io.Logger;
>>>>>>> 16661ebdc703439a2b07c1f5c9f685bb07897dc4
import com.techelevator.models.products.Product;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private static BigDecimal remainingFunds = new BigDecimal("0.00");
    private List<Product> purchasedProducts = new ArrayList<>();

    public static BigDecimal getRemainingFunds() {
        return remainingFunds;
    }
    public void setRemainingFunds(BigDecimal remainingFunds) {
        this.remainingFunds = remainingFunds;
    }

    public Transaction() {
    }

    public static void addMoney(BigDecimal amount){
        if (isMoneyValid(amount)) {
            remainingFunds = remainingFunds.add(amount);
        }
    }

    public static void purchaseItem(String itemID)
    {
        final int ITEM_QUANTITY_PER_SELECTION = 1;

        List<Product> productList = Inventory.getProductList();
        Product selection = null;
        int quantity = 0;
        BigDecimal price = new BigDecimal("0.00");
        for (Product product : productList) {
            if (product.getSlotID().equals(itemID)) {
                selection = product;
                quantity = product.getQuantity();
                price = product.getPrice();
            }
        }
        // if item in stock
        if ((selection == null) || (quantity < 1)) return;  // add error handling

        // check if enough money to purchase
        if (price.compareTo(remainingFunds) > 0)
        {
            // update funds and product quantity
            remainingFunds = remainingFunds.subtract(price);
            Inventory.updateInventory(selection, ITEM_QUANTITY_PER_SELECTION);
            UserOutput.displayPurchaseSuccess();
        }


    }


    public static boolean isItemSelectionValid(String itemID) {
        if(itemID != null) {
            return ((itemID.startsWith("A") || itemID.startsWith("B") || itemID.startsWith("C") || itemID.startsWith("D"))
                    && (itemID.endsWith("1") || itemID.endsWith("2") || itemID.endsWith("3") || itemID.endsWith("4")));
        }
        return false;
    }

    public static boolean isMoneyValid(BigDecimal money){
        BigDecimal zero = new BigDecimal("0.00");
        BigDecimal oneDollar = new BigDecimal("1.00");
        BigDecimal fiveDollars = new BigDecimal("5.00");
        BigDecimal tenDollars = new BigDecimal("10.00");
        BigDecimal twentyDollars = new BigDecimal("20.00");

        boolean isValid = false;
        try
        {
            if (!money.equals(oneDollar) || !money.equals(fiveDollars) ||
                    !money.equals(tenDollars) || !money.equals(twentyDollars) || money.compareTo(zero) < 0)
            {
                throw new InvalidFundsException("Invalid bill type", money);
            } else isValid = true;
        } catch (InvalidFundsException exception){

            Logger.createLogEntry(exception.getMessage());
        }
        return isValid;
    }


}
