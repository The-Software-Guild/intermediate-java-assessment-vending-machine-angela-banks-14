package com.ab.vendingmachine.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class VendingMachineView {
    private UserIO io;


    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void addMoneyBanner() {
        io.print("====ADD FUNDS====");
    }

    // add money
    public BigDecimal getFunds() {
        addMoneyBanner();
        return io.readBigDecimal("Enter funds to add ($0 - $100): ", new BigDecimal(0), new BigDecimal(100));
    }

    public void allItemsBanner() {
        io.print("=======VENDING MACHINE ITEMS=========");
    }

    // print all items
    public void printAllItems(Map<String, BigDecimal> itemsInStock) {

        itemsInStock.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        });
    }

    public String getItemSelection() {
        return io.readString("Please select an item:");
    }


    public void purchaseSucceeded(String snackName){
        io.print("Purchase Successful. Enjoy your " + snackName + "!");
        io.print("Here is your change.");
    }

    public void printChanges(Map<BigDecimal, BigDecimal> changeDuePerCoin) {
        changeDuePerCoin.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "c : " + entry.getValue());
        });
    }

    public void displayQuitMessage() {
        io.print("Goodbye!" + "\n");
    }

    public void displayUnknownCommand() {
        io.print("Error! Unknown command.");
    }

    public void displayErrorMessage(String message) {
        io.print("Error! Item out of stock. Please select another item.");
    }

    // delete
    public void displayInsufficientFundsMessage(BigDecimal funds) {
        io.print("Insufficient funds. You have only inputted $" + funds);
    }

    //delete
    public void displayOutOfStock(String message) {
        io.print("Sorry, " + message + " is out of stock" + '\n');
        //io.readString("Please select another item.");
    }

    // delete
    public void displayBalance(BigDecimal bal) {
        io.print("Current balance:"+bal.setScale(2, RoundingMode.DOWN));
    }
}

