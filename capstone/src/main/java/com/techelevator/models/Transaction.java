package com.techelevator.models;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.exceptions.InsufficientFundsException;
import com.techelevator.models.exceptions.InsufficientStockException;
import com.techelevator.models.exceptions.InvalidFundsException;
import com.techelevator.models.exceptions.InvalidOptionException;
import com.techelevator.models.file_io.Logger;
import com.techelevator.models.file_io.SalesReport;
import com.techelevator.models.file_io.TransactionLog;
import com.techelevator.models.products.Product;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;

import java.util.*;

public class Transaction {


    private  BigDecimal remainingFunds = new BigDecimal("0.00");
    private Map<Product, Integer> productsPurchased = new HashMap<>();

    public BigDecimal getRemainingFunds() {
        return remainingFunds;
    }
    public void setRemainingFunds(BigDecimal remainingFunds) {
        this.remainingFunds = remainingFunds;
    }

    public Map<Product, Integer> getProductsPurchased() {
        return productsPurchased;
    }

    public Transaction() {
    }

    public void addMoney(BigDecimal amount){
        if (isMoneyValid(amount)) {
            remainingFunds = remainingFunds.add(amount);
            TransactionLog.createLogEntry("FEED MONEY: " + amount + " " + remainingFunds);
        }
    }

    public void spendMoney(Product product){
        if (hasEnoughMoney(product))
        {
            BigDecimal price = product.getPrice();
            BigDecimal newFunds = remainingFunds.subtract(price);
            setRemainingFunds(newFunds);
        }
        else
        {
            System.out.println("Not enough money");
        }
    }

    public void updatePurchases(Product product) {
        if (productsPurchased.containsKey(product))
        {
            int amountPurchased = productsPurchased.get(product);
            productsPurchased.replace(product, amountPurchased + 1);
        }
        else productsPurchased.put(product, 1);
    }

    public void purchaseItem(String itemID)
    {
        final int ITEM_QUANTITY_PER_SELECTION = -1;

        if (isItemSelectionValid(itemID)) {

            List<Product> productList = Inventory.getProductList();

            Product selection = null;

            for (Product product : productList) {
                String productID = product.getSlotID();
                if (productID.equalsIgnoreCase(itemID)) {
                    selection = product;
                }
            }

            if (selection != null
                    && isInStock(selection)
                    && isItemSelectionValid(itemID)
                    && hasEnoughMoney(selection)
            ) {
                Inventory.updateInventory(selection, ITEM_QUANTITY_PER_SELECTION);

                updatePurchases(selection);

                spendMoney(selection);

                SalesReport.update(selection);

                String type = selection.getType();
                String name = selection.getName();
                String id = selection.getSlotID();
                BigDecimal price = selection.getPrice();
                TransactionLog.createLogEntry(name + " " + id + " " + price + " " + remainingFunds);
                System.out.println();
                UserOutput.displayPurchaseSuccess();
                UserOutput.displayItemTypeReturnMessage(type);
                System.out.println();
            }
        }
    }


    public boolean isItemSelectionValid(String itemID) {
        boolean isValidSelection = false;
        try {
            if (itemID != null) {
                itemID = (itemID).toLowerCase();
                if ((itemID.startsWith("a") || itemID.startsWith("b") || itemID.startsWith("c") || itemID.startsWith("d"))
                        && (itemID.endsWith("1") || itemID.endsWith("2") || itemID.endsWith("3") || itemID.endsWith("4"))
                ) isValidSelection = true;
                else {
                    throw new InvalidOptionException("That is not a valid selection. Try again...", itemID);
                }
            } else throw new InvalidOptionException("That is not a valid selection. Try again...", null);
        }
        catch (InvalidOptionException exception) {Logger.createLogEntry(exception.getMessage());}

        return isValidSelection;
    }

    public boolean isInStock(Product product) {
        boolean isInStock = false;
        int quantity = 0;
        if (product != null) quantity = product.getQuantity();
        try {
            if (quantity > 0)
            {
                isInStock = true;
            }
            else
            {
                System.out.println("\nThis item is out of stock.\n");
                throw new InsufficientStockException("This product is not available", quantity);}
        } catch (InsufficientStockException exception) {
            Logger.createLogEntry(exception.getMessage());
        }
        return isInStock;
    }

    public boolean isMoneyValid(BigDecimal money){
        BigDecimal zero = new BigDecimal("0.00");
        BigDecimal oneDollar = new BigDecimal("1.00");
        BigDecimal fiveDollars = new BigDecimal("5.00");
        BigDecimal tenDollars = new BigDecimal("10.00");
        BigDecimal twentyDollars = new BigDecimal("20.00");

        boolean isValid = false;
        try
        {
            if (!(money.equals(oneDollar) || money.equals(fiveDollars) ||
                    money.equals(tenDollars) || money.equals(twentyDollars))
                    || money.compareTo(zero) < 0)
            {
                System.out.println("\nPlease enter a valid bill type ($1, $5, $10, or $20).\n");
                throw new InvalidFundsException("Invalid bill type", money);
            } else isValid = true;
        } catch (InvalidFundsException exception){Logger.createLogEntry(exception.getMessage());}

        return isValid;
    }

    public boolean hasEnoughMoney(Product product){
        BigDecimal zero = new BigDecimal("0.00");
        BigDecimal itemCost = product.getPrice();
        boolean isValid = false;

        if (itemCost != null) {
            try {
                BigDecimal totalFunds = getRemainingFunds();

                if (totalFunds.compareTo(itemCost) >= 0) {
                    isValid = true;
                }
                else {
                    System.out.println("\nYou don't have enough funds to make that transaction. Please add more bills.\n");

                    throw new InsufficientFundsException("You don't have enough funds to make that transaction.", remainingFunds, itemCost);
                }
            } catch (InsufficientFundsException exception) {
                Logger.createLogEntry(exception.getMessage());
            }
        }
        return isValid;
    }

    public Map<Product, Integer> finishTransaction()
    {
        UserOutput.displaySummaryIntro();
        getItemSummary(productsPurchased);
        returnChange(remainingFunds);
        return productsPurchased;
    }

    public void getItemSummary(Map<Product, Integer> productsPurchased)
    {
        for (Map.Entry<Product, Integer> product: productsPurchased.entrySet()) {
            String productName = product.getKey().getName();
            String amountPurchased = product.getValue().toString();
            UserOutput.displayItemSummary(productName, amountPurchased);
        }
    }

    public void returnChange(BigDecimal remainingFunds) {

        int remaining = remainingFunds.multiply(new BigDecimal("100")).intValue();

        final int QUARTER = 25;
        final int DIME = 10;
        final int NICKEL = 5;

        int quartersReturned = remaining / QUARTER;
        int lessQuarters = remaining % QUARTER;
        int dimesReturned = lessQuarters / DIME;
        int lessDimes = lessQuarters % DIME;
        int nicklesReturned = lessDimes / NICKEL;

        UserOutput.displayChange(quartersReturned, dimesReturned, nicklesReturned);
    }


}
