package datamodel;

import java.util.List;
import model.Customer;
import model.Order;
import model.Product;

/**
 *
 * @author DELL
 */
public interface OrderM {
    void printAllPendingOrder(List<Order> orders);
    void creatOrder(List<Order> orders, List<Product> products, List<Customer> customers);
    void updateOrder(List<Order> orders, List<Product> products, List<Customer> customers);
    void deleteOrder(List<Order> orders);
    void printAllOrder(List<Order> orders, List<Customer> customers);
}
