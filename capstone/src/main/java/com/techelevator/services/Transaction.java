package com.techelevator.services;

import com.techelevator.exceptions.InsufficientFundsException;
import com.techelevator.exceptions.InsufficientStockException;
import com.techelevator.exceptions.InvalidFundsException;
import com.techelevator.exceptions.InvalidOptionException;
import com.techelevator.io.logs.Logger;
import com.techelevator.io.reports.SalesReport;
import com.techelevator.io.logs.TransactionLog;
import com.techelevator.models.Product;
import com.techelevator.services.Inventory;
import com.techelevator.validators.Validator;
import com.techelevator.io.ui.LoadingBar;
import com.techelevator.io.ui.UserOutput;


import java.math.BigDecimal;

import java.math.RoundingMode;
import java.util.*;

public class Transaction
{
    UserOutput userOutput = new UserOutput();

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


    public void addMoney(String selection) throws InvalidFundsException {
        BigDecimal amount;
        try{
            amount = new BigDecimal(selection).setScale(2, RoundingMode.UNNECESSARY);
            if (!Validator.isMoneyValid(amount)) throw new InvalidFundsException("Invalid bill type", amount);
            else {
                remainingFunds = remainingFunds.add(amount);
                TransactionLog.createLogEntry("FEED MONEY: " + amount + " " + remainingFunds);
            }
        }
        catch (Exception ex)
        {
            UserOutput.displayErrorMessage("Invalid user input");
            Logger.createLogEntry("Invalid user input for BigDecimal conversion");
        }
    }

    public void spendMoney(Product product){
        if (Validator.hasEnoughMoney(product, remainingFunds))
        {
            BigDecimal price = product.getPrice();
            BigDecimal newFunds = remainingFunds.subtract(price);
            setRemainingFunds(newFunds);
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

    public void purchaseItem(String itemID) throws Exception {

        // if item selection not valid menu option, throws Invalid Option Exception
        if (!Validator.isItemSelectionValid(itemID)) throw new InvalidOptionException("This is not a valid item selection.", itemID);

        final int ITEM_QUANTITY_PER_SELECTION = -1;

        List<Product> productList = Inventory.getProductList();

        Product selection = null;
        for (Product product : productList)
        {
            String productID = product.getSlotID();

            if (productID.equalsIgnoreCase(itemID)) {
                selection = product;
            }
        }

        if (selection == null) throw new Exception("Selection is null");
        else if (!Validator.isInStock(selection)) throw new InsufficientStockException("This item is out of stock", selection.getQuantity());
        else if (!Validator.hasEnoughMoney(selection, remainingFunds)) throw new InsufficientFundsException("Insufficient funds", getRemainingFunds(), selection.getPrice());

        else  // selected item found in menu, item is in stock, and user has enough money to purchase item
        {
            Inventory.updateInventory(selection, ITEM_QUANTITY_PER_SELECTION);

            updatePurchases(selection);

            spendMoney(selection);

            SalesReport.update(selection);

            String type = selection.getType();
            String name = selection.getName();
            String id = selection.getSlotID();
            BigDecimal price = selection.getPrice();

            TransactionLog.createLogEntry(name + " " + id + " " + price + " " + remainingFunds);

            LoadingBar.displayLoadingBar();
            userOutput.displayPurchaseSuccess(name);
            userOutput.displayItemTypeReturnMessage(type);
        }
    }

    public Map<Product, Integer> finishTransaction()
    {
        userOutput.displaySummaryIntro();
        getItemSummary(productsPurchased);
        returnChange(remainingFunds);

        Scanner input = new Scanner(System.in);
        System.out.println("Press enter to return to main menu...");
        input.nextLine();
        return productsPurchased;
    }

    public void getItemSummary(Map<Product, Integer> productsPurchased)
    {
        for (Map.Entry<Product, Integer> product: productsPurchased.entrySet()) {
            String productName = product.getKey().getName();
            String amountPurchased = product.getValue().toString();
            userOutput.displayItemSummary(productName, amountPurchased);
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

        userOutput.displayChange(quartersReturned, dimesReturned, nicklesReturned);
    }
}
