package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {

    private String ID, name, address, phone;

    public Customer setCustomer(Customer c) {
        this.name = c.name;
        this.address = c.address;
        this.phone = c.phone;
        return this;
    }

    @Override
    public String toString() {
        return this.ID + "," + this.name + "," + this.address + "," + this.phone;
    }
}
