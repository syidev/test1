package db.souvenir.ui.cli;

import db.souvenir.model.Vendor;
import db.souvenir.ui.EditVendorUi;

import java.util.*;

public class EditVendorCli extends Cli implements EditVendorUi {
    @Override
    public int printMenu() {
        return printMenu(
            "EDIT VENDOR",
            new TreeMap<>(Map.of(ADD, "Add", UPDATE,"Update", DELETE, "Delete", GET_ALL, "Get all")), EXIT);
    }

    @Override
    public Map<String, String> getAddForm() {
        printHeader("Add vendor");
        String name = getStringInput("Enter vendor name");
        String country = getStringInput("Enter vendor country");

        return new HashMap<>(Map.of("name", name, "country", country));
    }

    @Override
    public Map<String, String> getDeleteForm() {
        printHeader("Delete vendor");
        String name = getStringInput("Enter vendor name");

        return new HashMap<>(Map.of("name", name));
    }

    @Override
    public Vendor getUpdateForm(Vendor vendor) {
        System.out.println("Name: " + vendor.getName());
        String name = getStringInput("Enter new name");
        if (!name.isBlank()) vendor.setName(name);

        System.out.println("Country: " + vendor.getCountry());
        String country = getStringInput("Enter new country");
        if (!country.isBlank()) vendor.setCountry(country);

        return vendor;
    }

    @Override
    public String getVendorForm() {
        printHeader("Update vendor");

        return getStringInput("Enter vendor name");
    }

    @Override
    public void printData(Optional<List<Vendor>> vendors) {
        printHeader("Get vendors");
        vendors.ifPresentOrElse(this::printSimpleList, () -> printNotification("Not found"));
    }
}
