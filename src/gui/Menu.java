package gui;

import java.util.ArrayList;
import java.util.List;
import manage.*;
import datamodel.*;
import model.*;
import validation.Validation;

public class Menu {

    private static final List<Product> pList = new ArrayList<>();
    private static final List<Customer> cList = new ArrayList<>();
    private static final List<Order> oList = new ArrayList<>();

    private static final ProductM pm = new ProductManagement(pList);
    private static final CustomerM cm = new CustomerManagement(cList);
    private static final OrderM om = new OrderManagement(oList);
    
    private static final String[] program = {
        "List all products.",
        "List all cuctommers.",
        "Search a Customer based on his/her ID.",
        "Add a Customer",
        "Update a Customer",
        "List all Orders.",
        "List all pending Orders.",
        "Add an Order.",
        "Update an Order.",
        "Quit."
    };

    public static void display() {
        int choice;
        do {
            System.out.println("============ Order Management program ==============");
            for (int i = 0; i < program.length; i++) {
                System.out.println((i + 1) + ". " + program[i]);
            }
            boolean isContinue = true;
            choice = Validation.getInt("Enter your choice: ", 1, program.length, 1);
            switch (choice) {
                case 1:
                    pm.printProductsList(pList);
                    break;
                case 2:
                    cm.printCustomersList(cList);
                    break;
                case 3:
                    cm.searchCustomer(cList);
                    break;
                case 4:
                    while (isContinue) {
                        cm.createCustomer(cList);
                        isContinue = Validation.inputYN("Do you want to continue: ");
                    }
                    break;
                case 5:
                    cm.updateCustomer(cList);
                    break;
                case 6:
                    om.printAllOrder(oList, cList);
                    break;
                case 7:
                    om.printAllPendingOrder(oList);
                    break;
                case 8:
                    om.creatOrder(oList, pList, cList);
                    break;
                case 9:
                    subMenu();
                    break;
                case 10:
                    break;
            }
        } while (choice < program.length);
    }

    private static void subMenu() {
        int ops;
        do {            
            boolean isContinue = true;
            System.out.println("1. Update order information.");
            System.out.println("2. Delete order");
            System.out.println("3. Quit");
            ops = Validation.getInt("Enter your choice: ", 1, 3, 1);
            switch (ops) {
                case 1:
                    while (isContinue) {
                        om.updateOrder(oList, pList, cList);
                        isContinue = Validation.inputYN("Do you want to continue: ");
                    }
                    break;
                case 2:
                    while (isContinue) {
                        om.deleteOrder(oList);
                        isContinue = Validation.inputYN("Do you want to continue: ");
                    }
                    break;
                case 3:
                    break;
            }
        } while (ops < 3);
    }
}
