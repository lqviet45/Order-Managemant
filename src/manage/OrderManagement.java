package manage;

import datamodel.OrderM;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import validation.Validation;
import model.*;

/**
 *
 * @author DELL
 */
public class OrderManagement implements OrderM {

    private String orderID, customerID, productID;
    private int quantity;
    private String orderDate;
    private String status;

    public OrderManagement(List<Order> orders) {
        loadData(orders);
    }

    public void printAllPendingOrder(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("List order is empty!!");
            return;
        }
//        for (Order o : orders) {
//            if (o.getStatus().equalsIgnoreCase("false")) {
//                System.out.println(o);
//            }
//        }       
        orders.stream()
                .filter(o -> o.getStatus().equalsIgnoreCase("false"))
                .collect(Collectors.toList())
                .forEach(o -> System.out.println(o));
    }

    public void creatOrder(List<Order> orders, List<Product> products, List<Customer> customers) {
        int choice;
        orderID = getOrderID(orders, 1, true);

        showCustomers(customers);
        choice = Validation.getInt("Enter the index of customer: ", 1, customers.size(), 1);
        customerID = customers.get(choice - 1).getID();
        showProducts(products);
        choice = Validation.getInt("Enter the index of product: ", 1, products.size(), 1);
        productID = products.get(choice - 1).getID();

        quantity = Validation.getInt("Enter order quantity: ", 0, Integer.MAX_VALUE, 1);
        orderDate = Validation.inputDate(1);
        status = Validation.inputStatus(1);
        orders.add(new Order(orderID, customerID, productID, quantity, orderDate, status));
        if (saveToFile(orders)) {
            System.out.println("SUCCESS");
        } else {
            orders.remove(this);
        }
    }

    public void updateOrder(List<Order> orders, List<Product> products, List<Customer> customers) {
        if (orders.isEmpty()) {
            System.out.println("List order is empty!!");
            return;
        }
        int choice;
        orderID = getOrderID(orders, 2, false);
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID().equalsIgnoreCase(orderID)) {

                showCustomers(customers);
                choice = Validation.getInt("Enter the index of customer: ", 1, customers.size(), 2);
                if (choice == -1) {
                    customerID = orders.get(i).getCustomerID();
                } else {
                    customerID = customers.get(choice - 1).getID();
                }

                showProducts(products);
                choice = Validation.getInt("Enter the index of product: ", 1, products.size(), 2);
                if (choice == -1) {
                    productID = orders.get(i).getProductID();
                } else {
                    productID = products.get(choice - 1).getID();
                }
                quantity = Validation.getInt("Enter order quantity: ", 0, Integer.MAX_VALUE, 2);
                if (quantity == -1) {
                    quantity = orders.get(i).getQuantity();
                }

                orderDate = Validation.inputDate(2);
                if (orderDate.trim().isEmpty()) {
                    orderDate = orders.get(i).getOrderDate();
                }

                status = Validation.inputStatus(2);
                if (status.trim().isEmpty()) {
                    status = orders.get(i).getStatus();
                }
                Order temp = orders.get(i);
                Order o = new Order(orderID, customerID, productID, quantity, orderDate, status);
                if (!orders.get(i).equals(o)) {
                    orders.set(i, new Order(orderID, customerID, productID, quantity, orderDate, status));
                    if (saveToFile(orders)) {
                        System.out.println("SUCCESS");
                    } else {
                        orders.set(i, temp);
                    }
                    return;
                } else {
                    System.out.println("UPDATE FAIL because there are not thing be updated!!");
                    return;
                }
            }
        }
        System.out.println("UPDATE FAIL");
    }

    public void deleteOrder(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("List order is empty!!");
            return;
        }
        orderID = getOrderID(orders, 2, false);
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order next = iterator.next();
            if (next.getOrderID().equalsIgnoreCase(orderID)) {
                boolean isDelete = Validation.inputYN("Do you want to delete this id " + orderID + ": ");
                if (!isDelete) {
                    System.out.println("Stop Delete");
                    return;
                }
                iterator.remove();
                if (saveToFile(orders)) {
                    System.out.println("SUCCESS");
                    return;
                }
                orders.add(next);
                System.out.println("DELETE FAIL");
            }
        }
    }

    public void printAllOrder(List<Order> orders, List<Customer> customers) {
        if (orders.isEmpty()) {
            System.out.println("List order is empty!!");
            return;
        }
        sortByCustomerName(orders);
//        for (Order o : orders) {
//            System.out.println(o);
//        }
        orders.stream().forEach(o -> System.out.println(o));
    }

    private static boolean saveToFile(List<Order> orders) {
        try {
            File f = new File("orders.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Order o : orders) {
                bw.write(o.toString());
                bw.newLine();
            }

            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            System.err.println(e);
            return false;
        }
    }

    private static void loadData(List<Order> orders) {
        try {
            FileReader fr = new FileReader("orders.txt");
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                String line = br.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                String[] words = line.split("[,]");
                String orderID = words[0].trim(),
                        customerID = words[1].trim(),
                        productID = words[2].trim();
                int quantity = Integer.parseInt(words[3].trim());
                String orderDate = words[4],
                        status = words[5].trim();
                orders.add(new Order(orderID, customerID, productID, quantity, orderDate, status));
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private String getOrderID(List<Order> orders, int mode, boolean isContinue) {
        while (true) {
            String tempID = Validation.getString("Enter order's ID: ", "ID must had format Dxxx!!!", "^D\\d{3}", 1);
            //Mode:
            //1 - create
            //2 - delete or update or search
            if ((!checkDuplicateID(tempID, orders) && mode == 1) || (checkDuplicateID(tempID, orders) && mode == 2)) {
                return tempID;
            }
            if (mode == 1) {
                System.out.println("ID must not duplicate!!!");
            } else if (mode == 2 && isContinue == true) {
                System.out.println("ID is not exist!!!");
            }
            //this is use went you want user input only one time
            //and show the mess for user
            //(search, update, delete)
            if (!isContinue) {
                System.out.println("This order does not exist");
                return tempID;
            }
        }
    }

    private boolean checkDuplicateID(String ID, List<Order> orders) {
//        for (Order o : orders) {
//            if (o.getOrderID().equalsIgnoreCase(ID)) {
//                return true;
//            }
//        }
//        return false;
        return orders.stream()
                .anyMatch(o -> o.getOrderID().equalsIgnoreCase(ID));
    }

    private void showCustomers(List<Customer> customers) {
        int index = 1;
        for (Customer c : customers) {
            System.out.println(index++ + " - " + c.getID() + "\t" + c.getName());
        }
    }

    private void showProducts(List<Product> products) {
        int index = 1;
        for (Product p : products) {
            System.out.printf("%d - %-10s%-30s%-15s\n", index++, p.getID(), p.getName(), p.getUnit());
        }
    }

    private static void sortByCustomerName(List<Order> orders) {
        Collections.sort(orders, (Order o1, Order o2) -> o1.getCustomerName().compareTo(o2.getCustomerName()));
    }
}
