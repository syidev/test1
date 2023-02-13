package db.souvenir.ui.cli;

import db.souvenir.model.Item;
import db.souvenir.ui.EditItemUi;

import java.util.*;

public class EditItemCli extends Cli implements EditItemUi {
    @Override
    public int printMenu() {
        return printMenu(
            "EDIT VENDOR",
            new TreeMap<>(Map.of(ADD, "Add", UPDATE,"Update", DELETE, "Delete", GET_ALL, "Get all")), EXIT);
    }

    @Override
    public Map<String, ?> getAddForm() {
        printHeader("Add item");

        String vendorName = getStringInput("Enter vendor name");
        String name = getStringInput("Enter item name");
        int year = getIntegerInput("Enter manufacture year");
        double price = getDoubleInput("Enter price");
        String vendorData = getStringInput("Enter vendor data");

        return new HashMap<>(Map.of("name", name, "year", year, "price", price, "vendorData", vendorData, "vendorName", vendorName));
    }

    @Override
    public Map<String, String> getDeleteForm() {
        printHeader("Delete item");

        String vendorName = getStringInput("Enter vendor name");
        String itemName = getStringInput("Enter item name");

        return new HashMap<>(Map.of("vendorName", vendorName, "itemName", itemName));
    }

    @Override
    public Item getUpdateForm(Item item) {
        System.out.println("Name: " + item.getName());
        String name = getStringInput("Enter new name");
        if(!name.isBlank()) item.setName(name);

        System.out.println("Manufacture year: " + item.getMfgDate().getYear());
        String year = getStringInput("Enter new manufacture year");

        if (!year.isBlank()) {
            try {
                int intTear = Integer.parseInt(year);
                item.setMfgDate(item.getMfgDate().withYear(intTear));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }

        System.out.println("Vendor data: " + item.getVendorData());
        String vendorData = getStringInput("Enter new vendor data");
        if (!vendorData.isBlank()) item.setVendorData(vendorData);

        return item;
    }

    @Override
    public Map<String, String> getVendorAndItemForm() {
        printHeader("Update item");
        String vendorName = getStringInput("Enter vendor name");
        String itemName = getStringInput("Enter item name");

        return new HashMap<>(Map.of("vendorName", vendorName, "itemName", itemName));
    }

    @Override
    public void printData(Optional<List<Item>> items) {
        printHeader("Get item");

        items.ifPresentOrElse(this::printSimpleList, () -> printNotification("Items not found"));
    }
}
