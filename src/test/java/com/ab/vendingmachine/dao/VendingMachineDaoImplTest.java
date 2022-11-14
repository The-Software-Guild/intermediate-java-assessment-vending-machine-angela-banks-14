package com.ab.vendingmachine.dao;

import com.ab.vendingmachine.dto.Item;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineDaoImplTest {

    VendingMachineDao testDao = new FileDaoImpl("itemsTest.txt");

    public VendingMachineDaoImplTest() {
    }

    @Test
    public void getItemTest() throws VendingMachineException {
        Item snickers = new Item("Snickers");
        snickers.setCost(new BigDecimal("2.10"));
        snickers.setNumInventoryItems(0);

        Item retrievedItem = testDao.getItem("Snickers");

        assertNotNull(retrievedItem, "Item should not be null");
        assertEquals(retrievedItem, snickers, "Item retrieved should be snickers");

    }

    @Test
    public void removeItemFromInventoryTest() throws VendingMachineException {
        String itemName = "Doritos";
        int inventoryBefore = testDao.getItemInventory(itemName);
        testDao.removeItem(itemName);
        int inventoryAfter = testDao.getItemInventory(itemName);

        assertTrue(inventoryAfter < inventoryBefore, "Inventory after should be less than before");
        assertEquals(inventoryAfter, inventoryBefore - 1, "Inventory after should be one less than it was" + "before");

    }

}
