package model;

/**
 *
 * @author DELL
 */
public class Product {
    private String ID, name, unit, origin;
    private double price;

    public Product() {
    }

    public Product(String ID, String name, String unit, String origin, double price) {
        this.ID = ID;
        this.name = name;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getID() + "," + getName() + "," + getUnit() + "," + getOrigin() + "," + getPrice();
    }
    
}
