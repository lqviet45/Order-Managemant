package datamodel;

import java.util.List;
import java.util.Optional;
import model.Customer;

public interface CustomerM {
    void createCustomer(List<Customer> customers);
    void printCustomersList(List<Customer> customers);
    Optional<Customer> searchCustomer(List<Customer> customers);
    void updateCustomer(List<Customer> customers);
}
