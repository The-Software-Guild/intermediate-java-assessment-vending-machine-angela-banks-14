package com.ab.vendingmachine.dao;

import com.ab.vendingmachine.dao.AuditDao;
import com.ab.vendingmachine.dao.VendingMachineException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AuditDaoImpl implements AuditDao{

    private final String AUDIT_FILE;

    public AuditDaoImpl() {
        this.AUDIT_FILE = "audit.txt";
    }

    //constructor for test files
    public AuditDaoImpl(String auditTestFile) {
        this.AUDIT_FILE = auditTestFile;
    }

    @Override
    public void writeAuditEntry(String entry) throws VendingMachineException{
        //implement
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachineException("Could not get audit information", e);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }

}
