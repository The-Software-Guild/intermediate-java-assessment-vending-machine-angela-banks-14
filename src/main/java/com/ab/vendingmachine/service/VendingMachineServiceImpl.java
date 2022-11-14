package com.ab.vendingmachine.service;

import com.ab.vendingmachine.dao.AuditDao;
import com.ab.vendingmachine.dao.VendingMachineDao;
import com.ab.vendingmachine.dao.VendingMachineException;
import com.ab.vendingmachine.dto.Change;
import com.ab.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Map;


public class VendingMachineServiceImpl implements VendingMachineService {

    private AuditDao adao;
    private VendingMachineDao dao;

    public VendingMachineServiceImpl( AuditDao adao, VendingMachineDao dao) {
        this.adao = adao;
        this.dao = dao;
    }

    //@Override
    public void checkForSufficientFunds(Item item, BigDecimal inputFunds) throws VendingMachineInsufficientFundsException {
        if (inputFunds.compareTo(item.getCost()) < 0) {
            throw new VendingMachineInsufficientFundsException(
                    "Insufficient funds to buy" + item.getName() + "prices" + item.getCost()
            );
        }
    }

    @Override
    public Map<String, BigDecimal> getItemsInStock() throws VendingMachineException {
        Map<String, BigDecimal> itemsInStock = dao.getItemNamesInStock();
        return itemsInStock;
    }

    @Override
    public Map<BigDecimal, BigDecimal> getChange(Item item, BigDecimal funds) {
        BigDecimal itemCost = item.getCost();
        Map<BigDecimal, BigDecimal> changeDuePerCoin = (Map<BigDecimal, BigDecimal>) Change.changeDuePerCoin(itemCost, funds);
        return changeDuePerCoin;
    }


    @Override
    public Item getItem(String name, BigDecimal inputFunds) throws VendingMachineException, VendingMachineItemInventoryException, VendingMachineInsufficientFundsException{
        //implement
        Item selectedItem = dao.getItem(name);

        if (selectedItem == null) {
            throw new VendingMachineItemInventoryException(
                    "Sorry, there are no " + name + "'s in the vending machine");
        }
        checkForSufficientFunds(selectedItem, inputFunds);
        //removeItem(name);

        return selectedItem;
    }

    @Override
    public void removeItem(String name) throws VendingMachineException, VendingMachineItemInventoryException {
        //implement
        if (dao.getItemInventory(name) > 0) {
            dao.removeItem(name);
            adao.writeAuditEntry(" One " + name + " removed");
        } else {
            throw new VendingMachineItemInventoryException(
                    "Sorry, " + name + " is out of stock.");
        }
    }
}

