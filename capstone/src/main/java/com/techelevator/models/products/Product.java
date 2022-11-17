package com.techelevator.models.products;

import java.math.BigDecimal;

public class Product {
    private String slotID;
    private String name;
    private BigDecimal price;
    private String type;

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

    public Product(String name, BigDecimal price)
    {
        this.name = name;
        this.price = price;
    }

}
