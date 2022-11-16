package com.ab.vendingmachine.service;

import com.ab.vendingmachine.dao.AuditDao;
import com.ab.vendingmachine.dao.VendingMachineException;

public class VendingMachineAuditDaoStubImpl implements AuditDao {

    @Override
    public void writeAuditEntry(String entry) throws VendingMachineException {
        // empty
    }
}
