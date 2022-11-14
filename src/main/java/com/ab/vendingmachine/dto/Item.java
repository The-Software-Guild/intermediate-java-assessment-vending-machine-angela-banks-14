package com.ab.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;


public class Item {

    private String name;
    private BigDecimal cost;
    private int numInventoryItems;

    public Item(String name, String cost, int numInventoryItems) {
        //implement
        this.name = name;
        this.cost = new BigDecimal(cost);
        this.numInventoryItems = numInventoryItems;
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getNumInventoryItems() {
        return numInventoryItems;
    }

    public void setNumInventoryItems(int numInventoryItems) {
        this.numInventoryItems = numInventoryItems;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", cost=" + cost + ", numInventoryItems=" + numInventoryItems + '}';
    }

    @Override
    public int hashCode() {
        //implement
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.cost);
        hash = 97 * hash + this.numInventoryItems;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        //implement
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.numInventoryItems != other.numInventoryItems) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }
}

