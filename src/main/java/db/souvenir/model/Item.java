package db.souvenir.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String vendorData;
    private LocalDate mfgDate;
    private double price;

    public Item() {}

    public Item(String name, String vendorData, LocalDate mfgYear, double price) {
        this.name = name;
        this.vendorData = vendorData;
        this.mfgDate = mfgYear;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getVendorData() {
        return vendorData;
    }

    public void setVendorData(String vendorData) {
        this.vendorData = vendorData;
    }

    public LocalDate getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(LocalDate mfgDate) {
        this.mfgDate = mfgDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item: name='" + name + "', vendorData='" + vendorData + "', mfgDate=" + mfgDate + ", price=" + price;
    }
}
