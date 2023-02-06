package model;

import java.util.List;
import java.util.Objects;


/**
 *
 * @author DELL
 */
public class Order {
    private String orderID, customerID, productID;
    private int quantity;
    private String orderDate;
    private String status;

    public Order() {
    }

    public Order(String orderID, String customerID, String productID, int quantity, String orderDate, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCustomerName(List<Customer> customers) {
        for (Customer c : customers) {
            if(c.getID().equalsIgnoreCase(customerID)) {
                return c.getName();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getOrderID() + "," + getCustomerID() + "," + getProductID() + "," + getQuantity() + "," + getOrderDate() + "," + getStatus();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.orderID, other.orderID)) {
            return false;
        }
        if (!Objects.equals(this.customerID, other.customerID)) {
            return false;
        }
        if (!Objects.equals(this.productID, other.productID)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        return Objects.equals(this.status, other.status);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

}
