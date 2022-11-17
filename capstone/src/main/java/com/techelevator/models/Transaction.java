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

        List<Product> productList = Inventory.getProductList();
        final int ITEM_QUANTITY_PER_SELECTION = 1;

        // if item in stock
        if (product.getQuantity() > 0) {
            // check if enough money to purchase

            // update funds and product quantity
            Inventory.updateInventory(Product product, ITEM_QUANTITY_PER_SELECTION);
        }
    }


}
