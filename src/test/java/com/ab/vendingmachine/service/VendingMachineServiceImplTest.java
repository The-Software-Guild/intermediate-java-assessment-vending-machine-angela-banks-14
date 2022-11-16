package com.ab.vendingmachine.service;

import com.ab.vendingmachine.dao.*;
import com.ab.vendingmachine.dto.Item;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import static org.junit.Assert.fail;


public class VendingMachineServiceImplTest {

    VendingMachineDao testDao = new FileDaoImpl("itemsTest.txt");
    String testAuditFile = "auditTest.txt";
    AuditDao testAuditDao = new AuditDaoImpl(testAuditFile);
    VendingMachineService testService = new VendingMachineServiceImpl(testAuditDao, testDao);

    // empty constructor
    public VendingMachineServiceImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        fail("The test case is a prototype.");
    }

    // tests
    @Test
    public void checkIfEnoughMoneyTest() {
        Item hotCheetos = new Item("Hot Cheetos");
        hotCheetos.setCost(new BigDecimal("1.75"));
        hotCheetos.setNumInventoryItems(9);
        BigDecimal enoughMoney = new BigDecimal("2.00");
        BigDecimal notEnoughMoney = new BigDecimal("1.50");

        try {
            testService.checkForSufficientFunds(hotCheetos, enoughMoney);
        } catch (VendingMachineInsufficientFundsException e) {
            fail("User has sufficient funds. No exception should be thrown");
        }
        try {
            testService.checkForSufficientFunds(hotCheetos, notEnoughMoney);
            fail("Insufficient funds, exception should be thrown");
        } catch (VendingMachineInsufficientFundsException e) {
        }
    }

    @Test
    public void removeOneItemFromInventoryTest() throws VendingMachineException {

        String snickers = "Snickers";
        try {
            testService.removeItem(snickers);

            fail("There are no snickers left, exception should be thrown");
        } catch (VendingMachineItemInventoryException e) {
        }

        String doritos = "Doritos";
        try {
            testService.removeItem(doritos);
        } catch (VendingMachineItemInventoryException e) {
            if (testDao.getItemInventory(doritos) > 0){
                fail("Doritos are in stock, exception should not be thrown");
            }
        }
    }
}
