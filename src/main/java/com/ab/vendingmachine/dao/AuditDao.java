package com.ab.vendingmachine.dao;

import com.ab.vendingmachine.dao.VendingMachineException;

public interface AuditDao {
    public void writeAuditEntry(String entry) throws VendingMachineException;
}
