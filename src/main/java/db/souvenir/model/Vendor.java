package db.souvenir.model;

import java.io.Serializable;

public class Vendor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String country;

    public Vendor(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public Vendor setName(String name) {
        this.name = name;

        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Vendor: name='" + name + "', country='" + country + "'";
    }
}
