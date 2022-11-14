package com.ab.vendingmachine.dao;

import com.ab.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface VendingMachineDao {

    void removeItem(String name) throws VendingMachineException;
    List<Item> listAllItems() throws VendingMachineException;
    int getItemInventory(String name) throws VendingMachineException;
    Item getItem(String name) throws VendingMachineException;
    Map<String, BigDecimal> getItemNamesInStock() throws VendingMachineException;
}