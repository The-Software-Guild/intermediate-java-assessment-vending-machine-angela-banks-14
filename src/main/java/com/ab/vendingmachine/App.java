package com.ab.vendingmachine;

import com.ab.vendingmachine.controller.VendingMachineController;
import com.ab.vendingmachine.dao.*;
import com.ab.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.ab.vendingmachine.service.VendingMachineItemInventoryException;
import com.ab.vendingmachine.service.VendingMachineService;
import com.ab.vendingmachine.service.VendingMachineServiceImpl;
import com.ab.vendingmachine.ui.UserIO;
import com.ab.vendingmachine.ui.UserIOImpl;
import com.ab.vendingmachine.ui.VendingMachineView;


public class App {
    public static void main(String[] args) throws VendingMachineException {
        //implement
        UserIO io = new UserIOImpl();
        VendingMachineView view = new VendingMachineView(io);
        AuditDao auditDao = new AuditDaoImpl();
        VendingMachineDao dao = new FileDaoImpl();
        VendingMachineService service = new VendingMachineServiceImpl(auditDao, dao);
        VendingMachineController controller = new VendingMachineController(view, service);

        controller.run();

    }
}
