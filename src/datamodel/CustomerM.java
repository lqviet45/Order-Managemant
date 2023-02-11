package datamodel;

import java.util.List;
import model.Customer;

public interface CustomerM {
    void createCustomer(List<Customer> customers);
    void printCustomersList(List<Customer> customers);
    void searchCustomer(List<Customer> customers);
    void updateCustomer(List<Customer> customers);
}
