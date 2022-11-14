package com.ab.vendingmachine.service;

import com.ab.vendingmachine.dao.VendingMachineException;
import com.ab.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Map;

public interface VendingMachineService {

    void checkForSufficientFunds(Item item, BigDecimal inputFunds) throws VendingMachineInsufficientFundsException;
    void removeItem(String name) throws VendingMachineException,VendingMachineItemInventoryException;
    Map<String, BigDecimal> getItemsInStock() throws VendingMachineException;
    Item getItem(String name, BigDecimal inputFunds) throws VendingMachineException, VendingMachineItemInventoryException, VendingMachineInsufficientFundsException;
    Map<BigDecimal, BigDecimal> getChange(Item item, BigDecimal funds);
}

