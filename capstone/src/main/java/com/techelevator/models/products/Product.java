package com.techelevator.models.products;

import java.math.BigDecimal;

public class Product {
    private String slotID;
    private String name;
    private BigDecimal price;
    private String type;
    private int quantity = 5;

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String getSlotID()
    {
        return slotID;
    }

    // In case Umbrella Corp wants to re-organize the product display
    public void setSlotID(String slotID) {this.slotID = slotID;}

    public String getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    // In case Umbrella Corp wants to update the prices later
    public void setPrice(BigDecimal price) {this.price = price;}


    public Product()
    {

    }

    public Product(String slotID, String name, BigDecimal price, String type)
    {
        this.slotID = slotID;
        this.name = name;
        this.price = price;
        this.type = type;
    }

}
