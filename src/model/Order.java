package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import manage.CustomerManagement;

/**
 *
 * @author DELL
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Order {

    private String orderID, customerID, productID, CustomerName;
    private int quantity;
    private String orderDate;
    private String status;
    
    public Order(String orderID, String customerID, String productID, int quantity, String orderDate, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getCustomerName() {
        this.CustomerName = CustomerManagement.getCustomerName(customerID);
        return CustomerName;
    }
}
