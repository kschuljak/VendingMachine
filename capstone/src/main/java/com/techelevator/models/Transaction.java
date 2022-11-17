package com.techelevator.models;

import com.techelevator.models.products.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private static BigDecimal remainingFunds = new BigDecimal("0.00");
    private List<Product> purchasedProducts = new ArrayList<>();

    public BigDecimal getRemainingFunds() {
        return remainingFunds;
    }
    public void setRemainingFunds(BigDecimal remainingFunds) {
        this.remainingFunds = remainingFunds;
    }

    public Transaction() {
    }

    public static void addMoney(BigDecimal amount){
        remainingFunds = remainingFunds.add(amount);
    }

    public static void purchaseItem(String itemID){
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
            Inventory.updateInventory(selection, ITEM_QUANTITY_PER_SELECTION);
        }
    }


}
