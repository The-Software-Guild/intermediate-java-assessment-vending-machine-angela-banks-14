package com.ab.vendingmachine.controller;

import com.ab.vendingmachine.dao.FileDaoImpl;
import com.ab.vendingmachine.dao.VendingMachineDao;
import com.ab.vendingmachine.dao.VendingMachineException;
import com.ab.vendingmachine.dto.Change;
import com.ab.vendingmachine.dto.Coins;
import com.ab.vendingmachine.dto.Item;
import com.ab.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.ab.vendingmachine.service.VendingMachineItemInventoryException;
import com.ab.vendingmachine.service.VendingMachineService;
import com.ab.vendingmachine.service.VendingMachineServiceImpl;
import com.ab.vendingmachine.ui.UserIO;
import com.ab.vendingmachine.ui.UserIOImpl;
import com.ab.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.Map;


public class VendingMachineController {
    private UserIO io = new UserIOImpl();
    private VendingMachineView view;
    private VendingMachineService service;

    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        //implement
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean start = true;
        String itemSelection = "";
        BigDecimal inputFunds;
        view.allItemsBanner();

        try {
            getMenuItems();
        } catch (VendingMachineException e) {
            view.displayErrorMessage(e.getMessage());
        }
        inputFunds = getFunds();

            while (start) {
                try {
                    itemSelection = getItemSelection();

                    if (itemSelection.equalsIgnoreCase("Exit")) {
                        start = false;
                        break;
                    }
                    getItem(itemSelection, inputFunds);
                    start = false;
                    break;

                } catch (VendingMachineInsufficientFundsException | VendingMachineItemInventoryException | VendingMachineException e) {
                    view.displayErrorMessage(e.getMessage());
                }
            }
            exitMessage();
    }

    // display menu items
    private void getMenuItems() throws VendingMachineException {
        Map<String, BigDecimal> itemsInStock = service.getItemsInStock();
        view.printAllItems(itemsInStock);
    }

    private BigDecimal getFunds() {
        return view.getFunds();
    }

    private String getItemSelection() {
        return view.getItemSelection();
    }

    private void exitMessage() {
        view.displayQuitMessage();
    }

    private void getItem(String name, BigDecimal funds) throws VendingMachineInsufficientFundsException, VendingMachineItemInventoryException, VendingMachineException {
        //implement
        Item wantedItem = service.getItem(name, funds);
        Map<BigDecimal, BigDecimal> changeDueInPennies;
        changeDueInPennies = service.getChange(wantedItem, funds);
        view.printChanges(changeDueInPennies);

        view.purchaseSucceeded(name);
    }

}

