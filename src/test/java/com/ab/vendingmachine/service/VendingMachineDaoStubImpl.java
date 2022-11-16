package com.ab.vendingmachine.service;

import com.ab.vendingmachine.dao.VendingMachineDao;
import com.ab.vendingmachine.dao.VendingMachineException;
import com.ab.vendingmachine.dto.Item;


//public class VendingMachineDaoStubImpl implements VendingMachineDao {
//
//    public Item onlyItem;
//
//    public VendingMachineDaoStubImpl() {
//        onlyItem = new Item("Skittles");
//        onlyItem.setCost();
//        onlyItem.setNumInventoryItems(5);
//    }
//
//    public VendingMachineDaoStubImpl(Item testItem) {
//        this.onlyItem = testItem;
//    }
//
//    @Override
//    public Item addItem(String item2, Item item) throws VendingMachineException {
//        if (item2.equals(onlyItem.getName())) {
//            return onlyItem;
//        } else {
//            return null;
//        }
//    }
//}
