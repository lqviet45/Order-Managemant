package model;

import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Customer {
    private String ID, name, address, phone;

    public Customer() {
    }

    public Customer(String ID, String name, String address, String phone) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address.toUpperCase();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone.toUpperCase();
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return getID() + "," + getName() + "," + getAddress() + "," + getPhone();
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return Objects.equals(this.phone, other.phone);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    
    
}
