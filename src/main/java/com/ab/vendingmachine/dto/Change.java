package com.ab.vendingmachine.dto;

import com.ab.vendingmachine.service.VendingMachineInsufficientFundsException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


public class Change {

    public static BigDecimal changeDueInPennies(BigDecimal itemCost, BigDecimal amount) {
        BigDecimal changeDueInPennies = (amount.subtract(itemCost)).multiply(new BigDecimal("100"));
        System.out.println("Change due: $" + (changeDueInPennies.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP).toString()));
        return changeDueInPennies;
    }

    public static Map<BigDecimal, BigDecimal> changeDuePerCoin(BigDecimal itemCost, BigDecimal amount) {
        Coins[] coinEnumArray = Coins.values();
        ArrayList<String> coinStringList = new ArrayList<>();
        for (Coins coin : coinEnumArray) {
            coinStringList.add(coin.toString());
        }
        ArrayList<BigDecimal> coins = new ArrayList<BigDecimal>();



        BigDecimal changeDueInPennies = changeDueInPennies(itemCost, amount);
        BigDecimal coinAmount;
        BigDecimal zero = new BigDecimal("0");

        Map<BigDecimal, BigDecimal> amountPerCoin = new HashMap<>();

        for (BigDecimal coin : coins) {

            if (changeDueInPennies.compareTo(coin) >= 0) {
                if (!changeDueInPennies.remainder(coin).equals(zero)) {
                    coinAmount = changeDueInPennies.divide(coin, 0, RoundingMode.DOWN);
                    amountPerCoin.put(coin, coinAmount);
                    changeDueInPennies = changeDueInPennies.remainder(coin);
                    if (changeDueInPennies.compareTo(zero) < 0) {
                        break;
                    }
                } else if (changeDueInPennies.remainder(coin).equals(zero)) {
                    coinAmount = changeDueInPennies.divide(coin, 0, RoundingMode.DOWN);
                    amountPerCoin.put(coin, coinAmount);
                    if ((changeDueInPennies.compareTo(zero)) < 0) {
                        break;
                    }
                }
            } else {

            }
        }
        return amountPerCoin;
    }
}

